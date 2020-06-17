package com.sunsun.tools.demo.bean

import com.sunsun.tools.basemvvm.bean.BaseResp


data class LoginResp(var scene: Int, var userGid:String, var userToken: String,var userid:String,var subChannelCode:Int): BaseResp()