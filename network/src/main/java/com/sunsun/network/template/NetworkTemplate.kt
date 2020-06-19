package com.sunsun.network.template

import BaseReq
import android.text.TextUtils
import android.util.Log
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.network.NetworkUtilKotlin
import com.sunsun.network.R
import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.util.*
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

class NetworkTemplate : INetworkTemplate {

    protected val TAG = "NetworkTemplate"
    protected val JSON =
        MediaType.parse("application/json; charset=utf-8")

    private var mOkHttpClient: OkHttpClient? = null

    private var mIsNetworkConnected = true

    private val mHashMap =
        HashMap<String, Call>()

    constructor(okHttpClient: OkHttpClient) {
        mOkHttpClient = okHttpClient
    }

    private fun isExecuted(url: String?): Boolean {
        try {
            if (mHashMap != null && mHashMap.containsKey(url)) {
                return mHashMap[url] != null && !mHashMap[url]?.isCanceled!!
            }
        } catch (e: Exception) {
            return false
        }
        return false
    }

    override fun <T> checkNetwork(callBack: IHttpCallBack<T>?): Boolean {
        val message = "网络异常，请检查是否连接网络"
        val errorCode: Int = HttpCode.ERROR_CODE_NETWORK_ERROR
        mIsNetworkConnected = NetworkUtilKotlin.isConnect()
        if (!mIsNetworkConnected) {
            if (callBack is IHttpCallBack) {
                failure(
                    errorCode,
                    message,
                    HttpCode.ERROR_NO_NETWORK_CODE,
                    callBack,
                    null
                )
            }
        }
        return mIsNetworkConnected
    }

    override fun  <T> get(
        url: String?,
        bean: Class<T>?,
        callBack: IHttpCallBack<in T>?,
        checkRepeat: Boolean
    ) {
        callBack?.url = url
        // 将参数放入callback中
        if (!checkNetwork(callBack) || checkRepeat && isExecuted(url)) {
            collectRequsetInfo(url, "", "check network  false")
            return
        }
        Log.d(TAG, "###begin get:currentUrl=$url")
        val request = Request.Builder().url(url).build()
        val call = mOkHttpClient!!.newCall(request)
        val finalUrl = url!!
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                mHashMap.remove(finalUrl)
                collectRequsetInfo(url, "", e.message)
                failure(
                    HttpCode.getExceptionCode(e),
                    e.toString(),
                    HttpCode.ERROR_NETWORK_CODE,
                    callBack,
                    null
                )
            }

            override fun onResponse(call: Call, response: Response) {
                mHashMap.remove(finalUrl)
                if (response.code() != 200) {
                    collectRequsetInfo(url, "", "response code " + response.code())
                    failure(
                        response.code(),
                        ResourceUtil.getString(R.string.common_error_data),
                        HttpCode.ERROR_NET_CODE,
                        callBack,
                        null
                    )
                    return
                }
                var jsonStr: String? = null
                // 获取response body中的数据
                try {
                    jsonStr = response.body().string()
                } catch (e: IOException) {
                    Log.e(TAG, e.message, e)
                }
                collectRequsetInfo(url, "", jsonStr)
                response(call, response.code(), jsonStr, bean, null, callBack)
            }
        })
        mHashMap[url] = call
    }


    override fun <T> post(
        url: String?,
        dataReq: BaseReq?,
        bean: Class<T>?,
        type: Type?,
        callBack: IHttpCallBack<in T>?
    ) {
        post(url, dataReq, bean, type, callBack, true)
    }

    override fun  <T> post(
        url: String?,
        dataReq: BaseReq?,
        bean: Class<T>?,
        callBack: IHttpCallBack<in T>?
    ) {
        post(url, dataReq, bean, null, callBack, true)
    }

    override fun  <T> post(
        url: String?,
        dataReq: BaseReq?,
        bean: Class<T>?,
        type: Type?,
        callBack: IHttpCallBack<in T>?,
        checkRepeat: Boolean
    ) {
        // 将参数放入callback中
        callBack?.url = url
        if (!checkNetwork(callBack)) {
            return
        }
        if (checkRepeat && isExecuted(url)) {
            collectRequsetInfo(url, "", "check network  false")
            failure(
                HttpCode.ERROR_CODE_RESPONSE_REPEAT, "", HttpCode.ERROR_NO_PARAMS_CODE,
                callBack, null
            )
            return
        }
        val content: String? = JsonUtil.instance.parseObjToJsonStr(dataReq)
        // 将参数放入callback中
        callBack?.params = content
        if (TextUtils.isEmpty(content)) {
            failure(
                HttpCode.ERROR_CODE_REQUEST_PARAM,
                ResourceUtil.getString(R.string.common_request_params_format_error),
                HttpCode.ERROR_NO_PARAMS_CODE,
                callBack,
                null
            )
            collectRequsetInfo(url, "", "content is empty")
            return
        }
        Log.d(TAG, "###begin post:currentUrl=$url")
        val requestBody = RequestBody.create(JSON, content)
        var request = Request.Builder().url(url).post(requestBody).build()
        val call = mOkHttpClient!!.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                mHashMap.remove(url)
                collectRequsetInfo(url, content, e.message)
                failure(
                    HttpCode.getExceptionCode(e),
                    e.message,
                    HttpCode.ERROR_NETWORK_CODE,
                    callBack,
                    null
                )
            }

            override fun onResponse(call: Call, response: Response) {
                mHashMap.remove(url)
                if (response.code() != 200) {
                    collectRequsetInfo(url, content, "response code " + response.code())
                    failure(
                        response.code(),
                        ResourceUtil.getString(R.string.common_error_data),
                        HttpCode.ERROR_NET_CODE,
                        callBack,
                        null
                    )
                    return
                }
                var jsonStr: String? = null
                // 获取response body中的数据
                try {
                    jsonStr = response.body().string()
                } catch (e: IOException) {
                    Log.e(TAG, e.message, e)
                }
                collectRequsetInfo(url, content, jsonStr)
                response(call, response.code(), jsonStr, bean, type, callBack)
            }
        })
        mHashMap[url!!] = call
    }

    override fun  <T> response(
        call: Call?,
        code: Int,
        response: String?,
        bean: Class<T>?,
        type: Type?,
        callBack: IHttpCallBack<in T>?
    ) {
        // body 数据异常
        if (TextUtils.isEmpty(response)) {
            failure(
                HttpCode.ERROR_CODE_RESPONSE_STRING,
                ResourceUtil.getString(R.string.common_error_data),
                HttpCode.ERROR_BUSINESS_CODE,
                callBack,
                null
            )
            return
        }
        var resultBean: T? = JsonUtil.instance.parseJsonStrToObj(response, bean, type)
        success(code, HttpCode.BUSINESS_CODE, resultBean, callBack)
    }

    override fun  <T> failure(
        code: Int,
        msg: String?,
        bizOrNetCode: Int,
        callBack: IHttpCallBack<T>?,
        bean: T?
    ) {
        callBack?.code = code.toString()
        ThreadManager.instance.post(ThreadManager.instance.THREAD_UI, Runnable {
            val errorMsg: String?
            val errorCode: Int
            if (!TextUtils.isEmpty(msg) && (msg!!.startsWith("Failed to connect to") || msg.contains(
                    "Failed to connect to"
                ))
            ) {
                errorMsg = ResourceUtil.getString(R.string.common_error_data)
                errorCode = HttpCode.ERROR_CODE_CONNECT_SERVER_FAILED
            } else {
                errorMsg = HttpCode.convertMsgByCode(code, msg)
                errorCode = code
            }
            callBack?.onFailure(errorMsg, errorCode, bean)
        })
    }


    override fun  <T> success(code: Int, bizOrNetCode: Int, bean: T?, callBack: IHttpCallBack<T>?) {
        ThreadManager.instance.post(ThreadManager.instance.THREAD_UI,
            Runnable { callBack?.onRespSuccess(code, bean) })
    }

    fun collectRequsetInfo(
        url: String?,
        post: String?,
        respone: String?
    ) {
        try {
            Log.d(
                TAG,
                "currentUrl = " + url + "\npost = " + FormatUtil.stringToJSON(post!!) + "\n respose= " + FormatUtil.stringToJSON(
                    respone!!
                ) + "\n"
            );
        } catch (e: java.lang.Exception) {
            Log.e(TAG, e.message, e)
        }
    }


    override fun evictCall(urlKey: String?) {
        if (!TextUtils.isEmpty(urlKey)) {
            val call = mHashMap[urlKey]
            if (call != null && !call.isCanceled) {
                mHashMap[urlKey]?.cancel()
                mHashMap?.remove(urlKey)
            }
        }
    }


    override fun evictAll() {
        if (mOkHttpClient != null) {
            mOkHttpClient?.connectionPool()?.evictAll()
        }
        mHashMap?.clear()
    }


}