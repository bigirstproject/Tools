package com.sunsun.tools.demo.repository

import com.sunsun.basemvvm.basenetwork.IBaseRequest
import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.tools.demo.bean.AppInfoReq
import com.sunsun.tools.demo.bean.AppInfoResp

interface IAppInfoRequest : IBaseRequest {

    /**
     * 初始化
     */
    fun getAppInfo(data: AppInfoReq?, callBack: IHttpCallBack<BaseLoanResp<AppInfoResp>>?)
}