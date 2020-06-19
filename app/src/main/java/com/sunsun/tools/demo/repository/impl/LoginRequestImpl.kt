package com.sunsun.tools.demo.repository.impl

import com.google.gson.reflect.TypeToken
import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.network.template.INetworkTemplate
import com.sunsun.network.template.OKHttpFactory
import com.sunsun.tools.basemvvm.repository.BaseRepository
import com.sunsun.tools.demo.bean.CodeLoginReq
import com.sunsun.tools.demo.bean.LoginResp
import com.sunsun.tools.demo.repository.ILoginRequest

class LoginRequestImpl : BaseRepository(), ILoginRequest {

    private val mHttpManager: INetworkTemplate? = OKHttpFactory.instance.getBusinessHttpManger()


    override fun loginByCode(
        data: CodeLoginReq?,
        callBack: IHttpCallBack<BaseLoanResp<LoginResp>>?
    ) {
        val url = "https://leading.haomoney.com/sparrow/"
        val typeToken = object : TypeToken<BaseLoanResp<LoginResp>>() {}
        mHttpManager?.post(
            url
            , data
            , BaseLoanResp<LoginResp>()::class.java,
            typeToken.type
            , callBack
        )
    }

    override fun getHttpManager(): INetworkTemplate? {
        return mHttpManager
    }

    override fun evictCall(urlKey: String?) {
        mHttpManager?.evictCall(urlKey)
    }
}