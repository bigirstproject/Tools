package com.sunsun.tools.demo.ui

import android.os.Bundle
import com.sunsun.tools.R
import com.sunsun.tools.basemvvm.ui.BaseMVVMActivity
import com.sunsun.tools.databinding.ActivityLoginBinding
import com.sunsun.tools.demo.bean.CodeLoginReq
import com.sunsun.tools.demo.viewmodel.impl.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseMVVMActivity<LoginViewModel, ActivityLoginBinding>() {


    override fun layoutID(): Int {
        return R.layout.activity_login
    }

    override fun obtainViewModel(): LoginViewModel {
        return LoginViewModel(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login_des.setText("LoginActivity")
        var codeLoginReq = CodeLoginReq(
            "18665051225",
            "123",
            "123",
            grantType = 123,
            subChannel = "rwerw",
            deviceType = 1
        )
        viewMode.loginByVerifyCode(codeLoginReq)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewMode.destory()
    }

}