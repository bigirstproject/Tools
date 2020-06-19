package com.sunsun.tools.demo.repository.impl

import com.google.gson.reflect.TypeToken
import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.network.template.INetworkTemplate
import com.sunsun.network.template.OKHttpFactory
import com.sunsun.tools.basemvvm.repository.BaseRepository
import com.sunsun.tools.demo.bean.AppInfoReq
import com.sunsun.tools.demo.bean.AppInfoResp
import com.sunsun.tools.demo.repository.IAppInfoRequest

class AppInfoRequestImpl : BaseRepository(), IAppInfoRequest {

    private val mHttpManager: INetworkTemplate? = OKHttpFactory.instance.getBusinessHttpManger()


    override fun getAppInfo(
        data: AppInfoReq?,
        callBack: IHttpCallBack<BaseLoanResp<AppInfoResp>>?
    ) {
        val url = "https://zstzxd.zhiyoutec.com/harbor/userbase/agreements?b=2&c=2&ch=133"
        val typeToken = object : TypeToken<BaseLoanResp<AppInfoResp>>() {}
        mHttpManager?.post(
            url
            , data
            , BaseLoanResp<AppInfoResp>()::class.java,
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