package com.sunsun.tools.demo.viewmodel

import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.tools.demo.bean.CodeLoginReq
import com.sunsun.tools.demo.bean.LoginResp

interface ILoginViewModel {

    fun loginByVerifyCode(reqData: CodeLoginReq)
}