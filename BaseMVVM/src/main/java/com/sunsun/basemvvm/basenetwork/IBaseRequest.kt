package com.sunsun.basemvvm.basenetwork

import com.sunsun.network.template.INetworkTemplate

interface IBaseRequest {

    /**
     * 这个返回对象不能直接调用到原生使用的舒服的post请求方法
     */
    fun getHttpManager(): INetworkTemplate?

    fun evictCall(urlKey: String?)
}