package com.sunsun.recycleview.demo.repository

import com.sunsun.basemvvm.basenetwork.IBaseRequest
import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.recycleview.demo.bean.HomeInfoReq
import com.sunsun.recycleview.demo.bean.HomeInfoResp

interface IHomePageRequest : IBaseRequest {

    fun getHomePage(req: HomeInfoReq, callBack: IHttpCallBack<BaseLoanResp<HomeInfoResp>>)
}