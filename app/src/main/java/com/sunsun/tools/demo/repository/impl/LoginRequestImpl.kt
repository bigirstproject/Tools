package com.sunsun.tools.demo.repository.impl

import com.google.gson.reflect.TypeToken
import com.sunsun.basemvvm.basemvvm.repository.BaseRepository
import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.network.template.INetworkTemplate
import com.sunsun.network.template.OKHttpFactory
import com.sunsun.tools.demo.bean.CodeLoginReq
import com.sunsun.tools.demo.bean.CodeLoginResp
import com.sunsun.tools.demo.repository.ILoginRequest

class LoginRequestImpl : BaseRepository(), ILoginRequest {

    private val mHttpManager: INetworkTemplate? = OKHttpFactory.instance.getBusinessHttpManger()


    override fun loginByCode(
        data: CodeLoginReq?,
        callBack: IHttpCallBack<BaseLoanResp<CodeLoginResp>>?
    ) {
        val url = "https://zstzxd.zhiyoutec.com//harbor/userbase/register/verifycode?b=2&c=2&ch=133"
        val typeToken = object : TypeToken<BaseLoanResp<CodeLoginResp>>() {}
        mHttpManager?.post(
            url
            , data
            , BaseLoanResp<CodeLoginResp>()::class.java,
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