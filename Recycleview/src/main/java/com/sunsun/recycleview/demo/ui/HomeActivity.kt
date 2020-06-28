package com.sunsun.recycleview.demo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunsun.basemvvm.basemvvm.ui.BaseMVVMActivity
import com.sunsun.recycleview.R
import com.sunsun.recycleview.component.adapter.MultiTypeAsyncAdapter
import com.sunsun.recycleview.component.bean.IItem
import com.sunsun.recycleview.databinding.HomeLayoutBinding
import com.sunsun.recycleview.demo.bean.HomeInfoReq
import com.sunsun.recycleview.demo.viewmode.impl.HomeViewMode
import kotlinx.android.synthetic.main.home_layout.*

class HomeActivity() : BaseMVVMActivity<HomeViewMode, HomeLayoutBinding>() {

    private val TAG: String = "HomeActivity1";

    var mAdapter: MultiTypeAsyncAdapter<IItem>? = null


    override fun obtainViewModel(): HomeViewMode {
        return HomeViewMode(application)
    }

    override fun layoutID(): Int {
        return R.layout.home_layout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        home_info_button.setOnClickListener(){
            viewMode.getHomePageInfo(HomeInfoReq("15896464564"))
        }
        repayment_button.setOnClickListener(){
            val intent = Intent(MainActivity@ this, RepaymentActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        initUI()
    }

    override fun initUI() {
        mAdapter = MultiTypeAsyncAdapter(object : DiffUtil.ItemCallback<IItem>() {
            override fun areItemsTheSame(oldItem: IItem, newItem: IItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: IItem, newItem: IItem): Boolean {
                return false
            }

        })
        dataBinding.recyclerContainer.run {
            recycler_container.setAdapter(mAdapter)
            recycler_container.setItemAnimator(null)
            layoutManager = LinearLayoutManager(context)
        }
        viewMode.homeDataLiveData?.observe(this, Observer<ArrayList<IItem>> {
                Log.d(TAG,  " size = " +it?.size)
                mAdapter?.setData(it)
        })
        viewMode.errorHomeDataLiveData?.observe(this, Observer {
            Log.d(TAG, it.toString() + " size")
        })
    }


}