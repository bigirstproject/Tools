package com.sunsun.tools.demo.viewmodel.impl

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sunsun.tools.basemvvm.bean.BaseLoanResp
import com.sunsun.tools.basemvvm.viewmodel.BaseViewModel
import com.sunsun.tools.demo.bean.CodeLoginReq
import com.sunsun.tools.demo.bean.LoginResp
import com.sunsun.tools.demo.repository.impl.LoginRequestImpl
import com.sunsun.tools.demo.viewmodel.ILoginViewModel

class LoginViewModel(application: Application) : BaseViewModel(application), ILoginViewModel {

    var LoginRequest: LoginRequestImpl? = null
    var ResultLiveData: MutableLiveData<String>? = null

    init {
        LoginRequest = LoginRequestImpl()
        ResultLiveData = MutableLiveData()
    }


    override fun loginByVerifyCode(reqData: CodeLoginReq, callBack: BaseLoanResp<LoginResp>) {
        LoginRequest?.loginByCode(reqData,callBack)
    }


    override fun destory() {
        super.destory()
        LoginRequest =null
        ResultLiveData =null
    }
}