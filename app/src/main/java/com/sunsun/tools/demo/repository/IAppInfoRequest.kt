package com.sunsun.tools.demo.repository

import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.tools.basenetwork.IBaseRequest
import com.sunsun.tools.demo.bean.AppInfoReq
import com.sunsun.tools.demo.bean.AppInfoResp
import com.sunsun.tools.demo.bean.CodeLoginReq
import com.sunsun.tools.demo.bean.CodeLoginResp

interface IAppInfoRequest : IBaseRequest {

    /**
     * 初始化
     */
    fun getAppInfo(data: AppInfoReq?, callBack: IHttpCallBack<BaseLoanResp<AppInfoResp>>?)
}