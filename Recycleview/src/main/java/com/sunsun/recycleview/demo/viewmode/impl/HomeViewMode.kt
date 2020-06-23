package com.sunsun.recycleview.demo.viewmode.impl

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sunsun.basemvvm.basemvvm.viewmodel.BaseViewModel
import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.recycleview.component.bean.IItem
import com.sunsun.recycleview.demo.bean.HomeInfoReq
import com.sunsun.recycleview.demo.bean.HomeInfoResp
import com.sunsun.recycleview.demo.repository.impl.HomePageRequestImpl
import com.sunsun.recycleview.demo.viewmode.IHomeViewMode

class HomeViewMode(application: Application) : BaseViewModel(application) ,
    IHomeViewMode {


    companion object {
        //小列表
//        const val JUMP_SMALL_LIST = PageCodeConstant.PAGE_CODE_CREDIT
    }
    var mHomePageRequest: HomePageRequestImpl? = HomePageRequestImpl()
    var homeDataLiveData: MutableLiveData<ArrayList<IItem>>? = MutableLiveData()
    var errorHomeDataLiveData: MutableLiveData<String>? = MutableLiveData()

    override fun initData() {
    }


    override fun getHomePageInfo(req: HomeInfoReq) {
        mHomePageRequest?.getHomePage(req, object : IHttpCallBack<BaseLoanResp<HomeInfoResp>>() {
            override fun onFailure(errorStr: String?, code: Int) {
                errorHomeDataLiveData?.value = errorStr
            }

            override fun onSuccess(code: Int, bean: BaseLoanResp<HomeInfoResp>?) {
                homeDataLiveData?.value =  bean?.content?.itemlist
            }

        })
    }

    override fun destory() {
        homeDataLiveData = null
        mHomePageRequest =null
    }



}