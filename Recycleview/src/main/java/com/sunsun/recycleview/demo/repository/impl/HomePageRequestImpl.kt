package com.sunsun.recycleview.demo.repository.impl

import com.sunsun.basemvvm.basemvvm.repository.BaseRepository
import com.sunsun.network.bean.BaseLoanResp
import com.sunsun.network.callback.IHttpCallBack
import com.sunsun.network.template.INetworkTemplate
import com.sunsun.network.template.OKHttpFactory
import com.sunsun.recycleview.component.bean.BannerItem
import com.sunsun.recycleview.component.bean.IItem
import com.sunsun.recycleview.component.bean.MessageItem
import com.sunsun.recycleview.demo.bean.HomeInfoReq
import com.sunsun.recycleview.demo.bean.HomeInfoResp
import com.sunsun.recycleview.demo.repository.IHomePageRequest

class HomePageRequestImpl : BaseRepository(), IHomePageRequest {

    private val mINetworkTemplate: INetworkTemplate? =
        OKHttpFactory.instance.getBusinessHttpManger()

    override fun getHomePage(
        req: HomeInfoReq,
        callBack: IHttpCallBack<BaseLoanResp<HomeInfoResp>>
    ) {
        val data = ArrayList<IItem>()
        data.add(getMessageItem())
        data.add(getMessageItem())
        val calldata: BaseLoanResp<HomeInfoResp> = BaseLoanResp<HomeInfoResp>()
        calldata.content = HomeInfoResp(data)
        callBack.onSuccess(200, calldata)
    }

    override fun getHttpManager(): INetworkTemplate? {
        return mINetworkTemplate
    }


    private fun getMessageItem(): IItem {
        val messageItem = MessageItem()
        return messageItem
    }


    private fun getBannerItem(): IItem {
        val messageItem = BannerItem()
        return messageItem
    }

    override fun evictCall(urlKey: String?) {
    }

}