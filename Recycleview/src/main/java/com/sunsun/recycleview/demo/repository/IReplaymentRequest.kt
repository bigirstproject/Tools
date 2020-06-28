package com.sunsun.recycleview.demo.repository

import com.sunsun.basemvvm.basenetwork.IBaseRequest
import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.recycleview.demo.bean.HomeInfoReq
import com.sunsun.recycleview.demo.bean.HomeInfoResp
import com.sunsun.recycleview.demo.bean.RepaymentReq
import com.sunsun.recycleview.demo.bean.RepaymentResp

interface IReplaymentRequest : IBaseRequest {

    fun getReplayment(req: RepaymentReq, callBack: IHttpCallBack<BaseLoanResp<RepaymentResp>>)
}