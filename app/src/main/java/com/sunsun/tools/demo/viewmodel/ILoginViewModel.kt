package com.sunsun.tools.demo.viewmodel

import com.sunsun.tools.basemvvm.bean.BaseLoanResp
import com.sunsun.tools.demo.bean.CodeLoginReq
import com.sunsun.tools.demo.bean.LoginResp

interface ILoginViewModel {

    fun loginByVerifyCode(reqData: CodeLoginReq, callBack: BaseLoanResp<LoginResp>)
}