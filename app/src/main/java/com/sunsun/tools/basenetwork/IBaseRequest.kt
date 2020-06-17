package com.sunsun.tools.basenetwork

import com.sunsun.network.IHttpManager

interface IBaseRequest {

    /**
     * 这个返回对象不能直接调用到原生使用的舒服的post请求方法
     */
    fun getHttpManager(): IHttpManager?

    fun evictCall(urlKey: String?)
}