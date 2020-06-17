package com.sunsun.network

/**
 * 网络请求统一统计类
 */
abstract class StatisCallBack<T> {

    var url: String? = null

    var code: String? = null

    var response: String? = null // 返回数据

    var params: String? = null // request参数


}