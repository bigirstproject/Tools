package com.sunsun.network.util

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Process
import android.util.Log
import java.util.*

class ThreadManager {

    companion object {
        val instance: ThreadManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ThreadManager()
        }
    }


    val TAG = "ThreadManager"

    // 介于后台和默认优先级之间的线程
    val THREAD_WORK = 1

    // 主线程
    val THREAD_UI = 2
    var mExceptionHandler: ExceptionHandler? = null

    // SAFE_STATIC_VAR
    private var sWorkThread: HandlerThread? = null

    // SAFE_STATIC_VAR
    private var sWorkHandler: Handler? = null

    // SAFE_STATIC_VAR
    private var mMainThreadHandler: Handler? = null
    private val mRunnableCache: HashMap<Any, RunnableMap>? =
        HashMap()


    /**
     * post方法
     *
     * @param threadType           task运行的线程类型
     * @param preCallback          第一个执行的Runnable
     * @param task                 第二个执行的Runnable
     * @param postCallback         第三个执行的Runnable
     * @param callbackToMainThread Callback类型Runnable是否运行在UI线程
     * @param delayMillis          整体延迟执行多少毫秒
     */
    fun post(
        threadType: Int,
        preCallback: Runnable?,
        task: Runnable?,
        postCallback: Runnable?,
        callbackToMainThread: Boolean,
        delayMillis: Long
    ) {
        if (task == null) {
            return
        }
        if (mMainThreadHandler == null) {
            createMainThread()
        }
        val handler: Handler?
        handler = when (threadType) {
            THREAD_WORK -> {
                if (sWorkThread == null) {
                    createWorkerThread()
                }
                sWorkHandler
            }
            THREAD_UI -> mMainThreadHandler
            else -> mMainThreadHandler
        }
        if (handler == null) {
            return
        }
        val finalHandler: Handler = handler
        var myLooper: Looper? = null
        if (!callbackToMainThread) {
            myLooper = Looper.myLooper()
            if (myLooper == null) {
                myLooper = mMainThreadHandler!!.looper
            }
        }
        val looper = myLooper
        val postRunnable = Runnable {
            try {
                task.run()
            } catch (e: Exception) {
                e.printStackTrace()
                onExceptionCaught(e)
            }
            if (postCallback != null) {
                if (callbackToMainThread || looper == mMainThreadHandler!!.looper) {
                    mMainThreadHandler!!.post(postCallback)
                } else {
                    Handler(looper).post(postCallback)
                }
            }
            try {
                mRunnableCache!!.remove(task)
            } catch (e: Exception) {
                e.printStackTrace()
                onExceptionCaught(e)
            }
        }
        val realRunnable = Runnable {
            if (preCallback != null) {
                if (callbackToMainThread || looper == mMainThreadHandler!!.looper) {
                    mMainThreadHandler!!.post {
                        preCallback.run()
                        finalHandler.post(postRunnable)
                    }
                } else {
                    Handler(looper).post {
                        preCallback.run()
                        finalHandler.post(postRunnable)
                    }
                }
            } else {
                postRunnable.run()
            }
        }
        synchronized(mRunnableCache!!) {
            if (preCallback == null) {
                mRunnableCache.put(task, RunnableMap(realRunnable, threadType))
            } else {
                mRunnableCache.put(task, RunnableMap(postRunnable, threadType))
            }
        }
        finalHandler.postDelayed(realRunnable, delayMillis)
    }

    fun post(
        threadType: Int,
        task: Runnable?,
        postCallbackRunnable: Runnable?
    ) {
        post(threadType, null, task, postCallbackRunnable, false, 0)
    }

    fun post(threadType: Int, task: Runnable?) {
        post(threadType, null, task, null, false, 0)
    }

    fun postDelayed(
        threadType: Int,
        task: Runnable?,
        delayMillis: Long
    ) {
        post(threadType, null, task, null, false, delayMillis)
    }

    fun postDelayed(
        threadType: Int,
        task: Runnable?,
        postCallback: Runnable?,
        delayMillis: Long
    ) {
        post(threadType, null, task, postCallback, false, delayMillis)
    }

    /**
     * 可以直接移除所有使用ThreadManager post出去的task，不用指定是线程类型threadType.
     */
    fun removeRunnable(task: Runnable?) {
        if (task == null) {
            return
        }
        val map = mRunnableCache!![task] ?: return
        val realRunnable = map.runnable
        if (realRunnable != null) {
            when (map.type) {
                THREAD_WORK -> if (sWorkHandler != null) {
                    sWorkHandler!!.removeCallbacks(realRunnable)
                }
                THREAD_UI -> if (mMainThreadHandler != null) {
                    mMainThreadHandler!!.removeCallbacks(realRunnable)
                }
                else -> {
                }
            }
            try {
                mRunnableCache.remove(task)
            } catch (e: Exception) {
                e.printStackTrace()
                onExceptionCaught(e)
            }
        }
    }

    fun removeAllRunnable() {
        mRunnableCache?.clear()
    }


    @Synchronized
    private fun createWorkerThread() {
        if (sWorkThread == null) {
            sWorkThread = HandlerThread(
                "WorkHandler",
                (Process.THREAD_PRIORITY_DEFAULT + Process.THREAD_PRIORITY_BACKGROUND) / 2
            )
            sWorkThread!!.start()
            sWorkHandler = Handler(sWorkThread!!.looper)
        }
    }

    @Synchronized
    private fun createMainThread() {
        if (mMainThreadHandler == null) {
            mMainThreadHandler = Handler(Looper.getMainLooper())
        }
    }

    fun isMainThread(): Boolean {
        return Looper.myLooper() == Looper.getMainLooper()
    }

    fun setExceptionHandler(exceptionHandler: ExceptionHandler?) {
        mExceptionHandler = exceptionHandler
    }

    fun onExceptionCaught(throwable: Throwable?) {
        Log.e(TAG, "onExceptionCaught", throwable)
        if (mExceptionHandler != null) {
            mExceptionHandler!!.caughtException(throwable)
        }
    }

    interface ExceptionHandler {
        fun caughtException(throwable: Throwable?)
    }

    private class RunnableMap(val runnable: Runnable, val type: Int)
}