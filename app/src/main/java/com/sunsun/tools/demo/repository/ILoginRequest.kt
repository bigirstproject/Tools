package com.sunsun.tools.demo.repository

import com.sunsun.basemvvm.basenetwork.IBaseRequest
import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.tools.demo.bean.CodeLoginReq
import com.sunsun.tools.demo.bean.CodeLoginResp

interface ILoginRequest : IBaseRequest {

    /**
     * 登录请求
     */
    fun loginByCode(data: CodeLoginReq?, callBack: IHttpCallBack<BaseLoanResp<CodeLoginResp>>?)
}