package com.sunsun.network.callback

abstract class IHttpCallBack<T> : StatisCallBack<T>(),
    ICommonCallBack<T> {

    override fun onFailure(errorStr: String?, code: Int, bean: T?) {
        onFailure(errorStr, code)
    }


    open fun onRespSuccess(code: Int, bean: T?) {
        onSuccess(code, bean)
    }

    abstract fun onFailure(errorStr: String?, code: Int)

}