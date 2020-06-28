package com.sunsun.recycleview.demo.viewmode.impl

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sunsun.basemvvm.basemvvm.viewmodel.BaseViewModel
import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.recycleview.demo.bean.BillListResp
import com.sunsun.recycleview.demo.bean.RepaymentReq
import com.sunsun.recycleview.demo.bean.RepaymentResp
import com.sunsun.recycleview.demo.repository.impl.ReplaymentRequestImpl
import com.sunsun.recycleview.demo.viewmode.IRepaymentViewMode

class ReplaymentViewMode(application: Application) : BaseViewModel(application),
    IRepaymentViewMode {


//    companion object {
//        //小列表
//        const val JUMP_SMALL_LIST = PageCodeConstant.PAGE_CODE_CREDIT
//    }

    var mReplaymentRequest: ReplaymentRequestImpl? = ReplaymentRequestImpl()
    var ReplaymentLiveData: MutableLiveData<ArrayList<BillListResp>>? = MutableLiveData()
    var errorReplaymentLiveData: MutableLiveData<String>? = MutableLiveData()

    override fun initData() {
    }


    override fun getRepaymentInfo(req: RepaymentReq) {
        mReplaymentRequest?.getReplayment(req, object : IHttpCallBack<BaseLoanResp<RepaymentResp>>() {
            override fun onFailure(errorStr: String?, code: Int) {
                errorReplaymentLiveData?.value = errorStr
            }

            override fun onSuccess(code: Int, bean: BaseLoanResp<RepaymentResp>?) {
                var arrayList: ArrayList<BillListResp>? = bean?.content?.itemlist
                    ReplaymentLiveData?.value = arrayList
            }

        })
    }

    override fun destory() {
        ReplaymentLiveData = null
        mReplaymentRequest = null
    }


}