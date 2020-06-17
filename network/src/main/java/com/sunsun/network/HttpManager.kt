package com.sunsun.network

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import com.sunsun.network.receiver.NetworkStateChangeReceiver
import com.sunsun.network.security.GzipRequestInterceptor
import com.sunsun.network.security.RetryIntercepter
import com.sunsun.network.security.TrustAllManager
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory

class HttpManager<T : IHttpManager> : IIHttpManager<T> {

    protected val HTTP_IMPL_NORMAL = 0
    protected val HTTP_IMPL_OK_HTTP = 1 //HTTP_IMPL_OK_HTTP


    val CONNECT_TIME_OUT = 30000L
    val READ_TIME_OUT = 30000L

    val RETIRY_TIME = 3

    protected var sNetStateReceiver: NetworkStateChangeReceiver? = null

    protected var mOkHttpClient: OkHttpClient? = null

    @Volatile
    protected var instances: HttpManager<T>? = null
    protected var mHttpManager: T? = null

//    init {
//        if (instances == null) {
//            synchronized(HttpManager::class.java) {
//                if (instances == null) {
//                    instances = HttpManager()
//                }
//            }
//        }
//    }

    companion object {
        val instance:  HttpManager<IHttpManager> by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HttpManager<IHttpManager>() }
//        private var instance: HttpManager<OKHttpManager>? = null
//            get() {
//                if (field == null) {
//                    field = HttpManager<OKHttpManager>()
//                }
//                return field
//            }
//        fun get(): HttpManager<OKHttpManager>{
//            //细心的小伙伴肯定发现了，这里不用getInstance作为为方法名，是因为在伴生对象声明时，内部已有getInstance方法，所以只能取其他名字
//            return instance!!
//        }
    }


    override fun init(context: Context) {
        mOkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(GzipRequestInterceptor())
            .addInterceptor(RetryIntercepter(RETIRY_TIME))
            .connectTimeout(
                CONNECT_TIME_OUT,
                TimeUnit.MILLISECONDS
            )
            .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
            .sslSocketFactory(createSSLSocketFactory())
            .build()
        if (Build.VERSION.SDK_INT >= 21) {
            NetworkUtilKotlin.registerNetworkListener()
        } else {
            startNetStateReceiver(context)
        }
    }

    override fun createSSLSocketFactory(): SSLSocketFactory? {
        var sSLSocketFactory: SSLSocketFactory? = null
        try {
            val sc = SSLContext.getInstance("TLS")
            sc.init(
                null,
                arrayOf(TrustAllManager()),
                SecureRandom()
            )
            sSLSocketFactory = sc.socketFactory
        } catch (e: Exception) {
            Log.getStackTraceString(e)
        }

        return sSLSocketFactory
    }

    override fun getBusinessHttpManger(): T? {
        if (mHttpManager == null) {
            factoryHttpManager(HTTP_IMPL_OK_HTTP)
        }
        return mHttpManager
    }


    fun factoryHttpManager(httpImpl: Int) {
        when (httpImpl) {
            HTTP_IMPL_OK_HTTP -> mHttpManager =
                mOkHttpClient?.let { OKHttpManager(it) } as T
            else -> {

            }
        }
    }

    override fun evictAllRequest() {
        if (mHttpManager != null) {
            mHttpManager?.evictAll()
        }
    }

    override fun startNetStateReceiver(context: Context) {
        if (sNetStateReceiver != null) {
            stopNetStateReceiver(context)
        }
        sNetStateReceiver = NetworkStateChangeReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        try {
            context.registerReceiver(sNetStateReceiver, intentFilter)
        } catch (e: java.lang.Exception) {
            Log.getStackTraceString(e)
        }
    }

    override fun stopNetStateReceiver(context: Context) {
        if (sNetStateReceiver != null) {
            try {
                context.unregisterReceiver(sNetStateReceiver)
            } catch (e: java.lang.Exception) {
                Log.getStackTraceString(e)
            }
        }
        sNetStateReceiver = null
    }


}