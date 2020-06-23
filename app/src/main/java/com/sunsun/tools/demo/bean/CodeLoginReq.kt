package com.sunsun.tools.demo.bean

import com.sunsun.network.bean.BaseReq


data class CodeLoginReq(var mobile: String,
                        var verifyCode: String?,
                        var verifyToken: String?,
                        var grantType: Int = 1,
                        var subChannel:String?,
                        var deviceType:Int =1
 ): BaseReq()