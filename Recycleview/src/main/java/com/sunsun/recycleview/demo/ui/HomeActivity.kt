package com.sunsun.recycleview.demo.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.sunsun.basemvvm.basemvvm.ui.BaseMVVMActivity
import com.sunsun.recycleview.R
import com.sunsun.recycleview.component.bean.IItem
import com.sunsun.recycleview.databinding.HomeLayoutBinding
import com.sunsun.recycleview.demo.bean.HomeInfoReq
import com.sunsun.recycleview.demo.viewmode.impl.HomeViewMode

class HomeActivity() : BaseMVVMActivity<HomeViewMode, HomeLayoutBinding>() {

    private val TAG: String = "HomeActivity";

    override fun obtainViewModel(): HomeViewMode {
        return HomeViewMode(application)
    }

    override fun layoutID(): Int {
        return R.layout.home_layout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMode.getHomePageInfo(HomeInfoReq("15896464564"))
        viewMode.homeDataLiveData?.observe(this, object : Observer<ArrayList<IItem>> {

            override fun onChanged(ArrayList: ArrayList<IItem>?) {
                Log.d(TAG, ArrayList?.toArray().toString() + "")
            }
        })
    }


}