package com.sunsun.network

interface ICommonCallBack<Any> {

    fun onSuccess(code: Int, bean: Any?)

    fun onFailure(errorStr: String?, code: Int, bean: Any?)

}