package com.sunsun.network.util

import android.annotation.SuppressLint
import android.app.Application
import java.lang.reflect.InvocationTargetException

object CommonUtil {

    private var sApplication: Application? = null
    /**
     * Init utils.
     *
     * Init it in the class of Application.
     *
     * @param app application
     */
    private fun init(app: Application?) {
//        Log.i("cwtnetwork", "初始化Applicaiton:$app")
        if (app != null) {
            sApplication = app
        }
//            Utils.sApplication.registerActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE)
    }

    /**
     * Return the context of Application object.
     *
     * @return the context of Application object
     */
    fun getApp(): Application {
//        Log.i("cwtnetwork,", sApplication.toString())
        if (sApplication != null) return sApplication as Application
        try {
            @SuppressLint("PrivateApi")
            val activityThread = Class.forName("android.app.ActivityThread")
            val at = activityThread.getMethod("currentActivityThread").invoke(null)
            val app = activityThread.getMethod("getApplication").invoke(at)
                    ?: throw NullPointerException("u should init first")
            init(app as Application)
            if (sApplication != null) {
                return sApplication as Application
            }
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }

        throw NullPointerException("u should init first")
    }
}