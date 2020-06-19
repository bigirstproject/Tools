package com.sunsun.network.callback

interface ICommonCallBack<T> {

    fun onSuccess(code: Int, bean: T?)

    fun onFailure(errorStr: String?, code: Int, bean: T?)

}