package com.sunsun.tools.demo.bean

import BaseResp


data class AppInfoResp(
    var authorization: List<Authorization?>?,
    var information: List<Authorization?>?,
    val register: List<Authorization?>?
) : BaseResp()


data class Authorization(val name: String?, val url: String?, val type: String?) : BaseResp()
