package com.sunsun.tools.demo.bean

import com.sunsun.tools.basemvvm.bean.BaseReq


/**
 * grantType 为注册时授权通道:0:微信账户;1：独立注册;2：渠道API;3:共享用户;4:海外渠道
 * @author liaopeijian
 * @Date 2020-02-11
 */
data class CodeLoginReq(var mobile: String,
                        var verifyCode: String,
                        var verifyToken: String,
                        var grantType: Int = 1,
                        var subChannel:String?,
                        var deviceType:Int =1  //标注android
 ): BaseReq()