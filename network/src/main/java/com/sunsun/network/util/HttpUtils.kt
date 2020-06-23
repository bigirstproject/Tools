package com.sunsun.network.util


object HttpUtils {

    const val sConfigHaborServerUrl: String = "https://zstzxd.zhiyoutec.com//harbor/"

    /**
     * 获取用户借款信息
     */
    val URL_LOAN_BASEINFO get() = "$sConfigHaborServerUrl/harbor/loan/baseinfo"

}