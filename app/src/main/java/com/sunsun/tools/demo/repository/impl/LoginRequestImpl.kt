package com.sunsun.tools.demo.repository.impl

import com.sunsun.network.HttpManager
import com.sunsun.network.IHttpManager
import com.sunsun.tools.basemvvm.bean.BaseLoanResp
import com.sunsun.tools.basemvvm.repository.BaseRepository
import com.sunsun.tools.demo.bean.CodeLoginReq
import com.sunsun.tools.demo.bean.LoginResp
import com.sunsun.tools.demo.repository.ILoginRequest

class LoginRequestImpl : BaseRepository(), ILoginRequest {

    private val mHttpManager: IHttpManager? = HttpManager.instance.getBusinessHttpManger()


    override fun loginByCode(data: CodeLoginReq, callBack: BaseLoanResp<LoginResp>) {
//        val url = HttpUtils.URL_GET_VERIFY_CODE
//        val typeToken = object : TypeToken<BaseLoanResp<VerifyCodeResp>>(){}
//        mHttpManager.post(url
//            , data
//            , BaseLoanResp<VerifyCodeResp>()::class.java,
//            typeToken.type
//            , callBack)
    }

    override fun getHttpManager(): IHttpManager? {
        return mHttpManager
    }

    override fun evictCall(urlKey: String?) {
        mHttpManager?.evictCall(urlKey)
    }
}