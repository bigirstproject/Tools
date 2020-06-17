package com.sunsun.network.security

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.Response
import okio.BufferedSink
import okio.GzipSink
import okio.Okio
import java.io.IOException

class GzipRequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val originalRequest = chain.request()
        if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
            return chain.proceed(originalRequest)
        }
        val compressedRequest =
            originalRequest.newBuilder().header("Content-Type", "application/x-www-form-urlencoded")
                .header("compress", "none").method(originalRequest.method(), originalRequest.body())
                .build()
        return chain.proceed(compressedRequest)
    }

    open fun gzip(body: RequestBody): RequestBody? {
        return object : RequestBody() {
            override fun contentType(): MediaType {
                return body.contentType()
            }

            override fun contentLength(): Long {
                return -1
            }

            @Throws(IOException::class)
            override fun writeTo(sink: BufferedSink) {
                val gzipSink = Okio.buffer(GzipSink(sink))
                body.writeTo(gzipSink)
                gzipSink.close()
            }
        }
    }
}
