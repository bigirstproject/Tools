package com.sunsun.tools.demo.viewmodel

import com.sunsun.tools.demo.bean.CodeLoginReq

interface ILoginViewModel {

    fun loginByVerifyCode(reqData: CodeLoginReq)
}