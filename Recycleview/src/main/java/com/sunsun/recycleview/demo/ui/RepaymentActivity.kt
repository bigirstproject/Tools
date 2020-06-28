package com.sunsun.recycleview.demo.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.sunsun.basemvvm.basemvvm.ui.BaseMVVMActivity
import com.sunsun.recycleview.R
import com.sunsun.recycleview.databinding.RepaymentLayoutBinding
import com.sunsun.recycleview.demo.bean.RepaymentReq
import com.sunsun.recycleview.demo.viewmode.impl.ReplaymentViewMode

class RepaymentActivity : BaseMVVMActivity<ReplaymentViewMode, RepaymentLayoutBinding>() {

    companion object {
        const val TAG = "RepaymentActivity1"
    }


    var mRecyclerView: RecyclerView? = null


    override fun obtainViewModel(): ReplaymentViewMode {
        return ReplaymentViewMode(application)
    }

    override fun layoutID(): Int {
        return R.layout.repayment_layout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRecyclerView = dataBinding.recyclerContainer
        dataBinding.adapter = ReplaymentAdapter()
        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView?.itemAnimator = null
        mRecyclerView?.setItemViewCacheSize(30)
        viewMode.getRepaymentInfo(RepaymentReq("13664656564"))
        initUI()
    }


    override fun initUI() {
        viewMode.ReplaymentLiveData?.observe(this, Observer {
            Log.d(TAG, "sucess" + it.toString())
        })
        viewMode.errorReplaymentLiveData?.observe(this, Observer {
            Log.d(TAG, "fail" + it.toString())
        })
    }


}