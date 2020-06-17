package com.sunsun.tools.demo.repository

import com.sunsun.tools.basemvvm.bean.BaseLoanResp
import com.sunsun.tools.basenetwork.IBaseRequest
import com.sunsun.tools.demo.bean.CodeLoginReq
import com.sunsun.tools.demo.bean.LoginResp

interface ILoginRequest : IBaseRequest {

    /**
     * 登录请求
     */
    fun loginByCode(data: CodeLoginReq, callBack: BaseLoanResp<LoginResp>)
}