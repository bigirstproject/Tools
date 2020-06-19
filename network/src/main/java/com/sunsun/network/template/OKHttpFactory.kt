package com.sunsun.network.template

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import com.sunsun.network.NetworkUtilKotlin
import com.sunsun.network.receiver.NetworkStateChangeReceiver
import com.sunsun.network.security.GzipRequestInterceptor
import com.sunsun.network.security.RetryIntercepter
import com.sunsun.network.security.TrustAllManager
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory

class OKHttpFactory<T : INetworkTemplate> :
    IOKHttpFractory<T> {

    protected val HTTP_IMPL_NORMAL = 0
    protected val HTTP_IMPL_OK_HTTP = 1 //HTTP_IMPL_OK_HTTP


    val CONNECT_TIME_OUT = 30000L
    val READ_TIME_OUT = 30000L

    val RETIRY_TIME = 3

    protected var sNetStateReceiver: NetworkStateChangeReceiver? = null

    protected var mOkHttpClient: OkHttpClient? = null

    protected var mINetworkTemplate: T? = null

    companion object {
        val instance: OKHttpFactory<INetworkTemplate> by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OKHttpFactory<INetworkTemplate>()
        }
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
        var sSLSocketFactory: SSLSocketFactory? =null;
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
        if (mINetworkTemplate == null) {
            factoryHttpManager(HTTP_IMPL_OK_HTTP)
        }
        return mINetworkTemplate
    }


    fun factoryHttpManager(httpImpl: Int) = when (httpImpl) {
        HTTP_IMPL_OK_HTTP -> {
            mINetworkTemplate = NetworkTemplate(
                mOkHttpClient!!
            ) as T
        }
        HTTP_IMPL_NORMAL -> {

        } else ->{

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

    override fun evictAllRequest() {
        if (mINetworkTemplate != null) {
            mINetworkTemplate?.evictAll()
        }
    }


}