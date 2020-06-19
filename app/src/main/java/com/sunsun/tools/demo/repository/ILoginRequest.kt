package com.sunsun.tools.demo.repository

import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.tools.basenetwork.IBaseRequest
import com.sunsun.tools.demo.bean.CodeLoginReq
import com.sunsun.tools.demo.bean.LoginResp

interface ILoginRequest : IBaseRequest {

    /**
     * 登录请求
     */
    fun loginByCode(data: CodeLoginReq?,  callBack: IHttpCallBack<BaseLoanResp<LoginResp>>?)
}