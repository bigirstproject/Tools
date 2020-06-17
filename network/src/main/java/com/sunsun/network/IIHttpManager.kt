package com.sunsun.network

import android.content.Context
import javax.net.ssl.SSLSocketFactory

interface IIHttpManager<T> {

    fun init(context: Context)

    fun createSSLSocketFactory(): SSLSocketFactory?

    fun getBusinessHttpManger(): T?

    fun evictAllRequest()

    fun startNetStateReceiver(context: Context)

    fun stopNetStateReceiver(context: Context)
}

