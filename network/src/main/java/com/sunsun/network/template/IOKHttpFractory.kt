package com.sunsun.network.template

import android.content.Context
import javax.net.ssl.SSLSocketFactory

interface IOKHttpFractory<T> {

    fun init(context: Context)

    fun createSSLSocketFactory(): SSLSocketFactory?

    fun getBusinessHttpManger(): T?

    fun evictAllRequest()

    fun startNetStateReceiver(context: Context)

    fun stopNetStateReceiver(context: Context)
}

