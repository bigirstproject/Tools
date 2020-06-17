package com.sunsun.network.security

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RetryIntercepter : Interceptor {

    private val TAG = RetryIntercepter::class.java.simpleName

    //最大重试次数
    var maxRetry = 0
    private var retryNum = 0 //假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）


    constructor   (maxRetry: Int) {
        this.maxRetry = maxRetry
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val request = chain.request()
        Log.d(TAG, "retryNum = $retryNum")
        var response = chain.proceed(request)
        while (!response.isSuccessful && retryNum < maxRetry) {
            retryNum++
            Log.d(TAG, "retryNum = $retryNum")
            response = chain.proceed(request)
        }
        return response
    }
}