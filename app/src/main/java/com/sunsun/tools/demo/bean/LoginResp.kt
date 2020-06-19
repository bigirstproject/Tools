package com.sunsun.tools.demo.bean

import BaseResp


data class LoginResp(var scene: Int, var userGid:String, var userToken: String,var userid:String,var subChannelCode:Int): BaseResp()