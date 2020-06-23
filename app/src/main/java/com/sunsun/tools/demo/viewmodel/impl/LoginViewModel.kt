package com.sunsun.tools.demo.viewmodel.impl

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.basemvvm.basemvvm.viewmodel.BaseViewModel
import com.sunsun.tools.demo.bean.CodeLoginReq
import com.sunsun.tools.demo.bean.CodeLoginResp
import com.sunsun.tools.demo.repository.impl.LoginRequestImpl
import com.sunsun.tools.demo.viewmodel.ILoginViewModel

class LoginViewModel(application: Application) :BaseViewModel(application), ILoginViewModel {

    var LoginRequest: LoginRequestImpl? = null
    var ResultLiveData: MutableLiveData<CodeLoginResp>? = null
    var errorResultLiveData: MutableLiveData<String>?=null

    init {
        LoginRequest = LoginRequestImpl()
        ResultLiveData = MutableLiveData<CodeLoginResp>()
        errorResultLiveData = MutableLiveData<String>()
        initData()
    }

    override fun initData() {
    }


    override fun loginByVerifyCode(reqData: CodeLoginReq) {
        LoginRequest?.loginByCode(reqData,object :IHttpCallBack<BaseLoanResp<CodeLoginResp>>(){
            override fun onFailure(errorStr: String?, code: Int) {
                errorResultLiveData?.value = errorStr
            }

            override fun onSuccess(code: Int, bean: BaseLoanResp<CodeLoginResp>?) {
                ResultLiveData?.value =bean?.content
            }

        })
    }

    override fun destory() {
        LoginRequest =null
        ResultLiveData =null
    }

}