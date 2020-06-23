package com.sunsun.tools.demo.viewmodel.impl

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.basemvvm.basemvvm.viewmodel.BaseViewModel
import com.sunsun.tools.demo.bean.AppInfoReq
import com.sunsun.tools.demo.bean.AppInfoResp
import com.sunsun.tools.demo.repository.impl.AppInfoRequestImpl
import com.sunsun.tools.demo.viewmodel.IAppInitModel

class AppInfoViewModel(application: Application) : BaseViewModel(application), IAppInitModel {

    var appInfoRequest: AppInfoRequestImpl? = null
    var ResultLiveData: MutableLiveData<AppInfoResp>? = null
    var errorResultLiveData: MutableLiveData<String>? = null

    init {
        appInfoRequest = AppInfoRequestImpl()
        ResultLiveData = MutableLiveData<AppInfoResp>()
        errorResultLiveData = MutableLiveData<String>()
        initData()
    }

    override fun initData() {
    }


    override fun getConfigInfo(reqData: AppInfoReq) {
        appInfoRequest?.getAppInfo(reqData, object : IHttpCallBack<BaseLoanResp<AppInfoResp>>() {
            override fun onFailure(errorStr: String?, code: Int) {
                errorResultLiveData?.value = errorStr
            }

            override fun onSuccess(code: Int, bean: BaseLoanResp<AppInfoResp>?) {
                ResultLiveData?.value = bean?.content
            }

        })
    }


    override fun destory() {
        appInfoRequest = null
        errorResultLiveData = null
        ResultLiveData = null
    }

}