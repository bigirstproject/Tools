package com.sunsun.recycleview.demo.repository.impl

import com.sunsun.basemvvm.basemvvm.repository.BaseRepository
import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.network.template.INetworkTemplate
import com.sunsun.network.template.OKHttpFactory
import com.sunsun.recycleview.demo.bean.BillListResp
import com.sunsun.recycleview.demo.bean.LoanItemBean
import com.sunsun.recycleview.demo.bean.RepaymentReq
import com.sunsun.recycleview.demo.bean.RepaymentResp
import com.sunsun.recycleview.demo.repository.IReplaymentRequest

class ReplaymentRequestImpl : BaseRepository(), IReplaymentRequest {

    private val mINetworkTemplate: INetworkTemplate? =
        OKHttpFactory.instance.getBusinessHttpManger()


    override fun getReplayment(
        req: RepaymentReq,
        callBack: IHttpCallBack<BaseLoanResp<RepaymentResp>>
    ) {
        val data: ArrayList<BillListResp>? = ArrayList<BillListResp>()
        data?.add(addItem())
        val calldata: BaseLoanResp<RepaymentResp> = BaseLoanResp()
        calldata.content = RepaymentResp(data)
        callBack.onSuccess(200, calldata)
    }

    override fun getHttpManager(): INetworkTemplate? {
        return mINetworkTemplate
    }

    private fun addItem(): BillListResp {
        val billListResp: BillListResp  = BillListResp(
          ArrayList<LoanItemBean>()
        )
        return billListResp
    }

    override fun evictCall(urlKey: String?) {
    }

}