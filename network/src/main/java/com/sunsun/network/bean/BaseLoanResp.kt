package com.sunsun.network.bean

import java.io.Serializable

open class BaseLoanResp<T> : Serializable {
    var status: Int = -1
    var message: String? = ""
    var content: T? = null
}