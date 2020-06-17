package com.sunsun.network

import BaseReq
import okhttp3.Call
import java.lang.reflect.Type


interface IHttpManager {


    fun  <T>  checkNetwork(callBack: IHttpCallBack<T>?): Boolean

    fun <T> get(
        url: String?,
        bean: Class<T>?,
        callBack: IHttpCallBack<T>?,
        checkRepeat: Boolean
    )

    fun <T> post(
        url: String?,
        dataReq: BaseReq?,
        bean: Class<T>?,
        type: Type?,
        callBack: IHttpCallBack<T>?,
        checkRepeat: Boolean
    )

    fun <T> post(
        url: String?,
        dataReq: BaseReq?,
        bean: Class<T>?,
        callBack: IHttpCallBack<T>?
    )

    fun <T> post(
        url: String?,
        dataReq: BaseReq?,
        bean: Class<T>?,
        type: Type?,
        callBack: IHttpCallBack<T>?
    )



    fun <T> response(
        call: Call?,
        code: Int,
        response: String?,
        bean: Class<T>?,
        type: Type?,
        callBack: IHttpCallBack<T>?
    )

    fun <T> failure(
        code: Int,
        msg: String?,
        bizOrNetCode: Int,
        callBack: IHttpCallBack<T>?,
        bean: T?
    )

    fun <T> success(
        code: Int,
        bizOrNetCode: Int,
        bean: T?,
        callBack: IHttpCallBack<T>?
    )


    fun evictAll()

    fun evictCall(urlKey: String?)
}