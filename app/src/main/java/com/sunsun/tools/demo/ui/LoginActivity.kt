package com.sunsun.tools.demo.ui

import android.os.Bundle
import com.sunsun.tools.R
import com.sunsun.tools.basemvvm.ui.BaseMVVMActivity
import com.sunsun.tools.databinding.ActivityLoginBinding
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
    }

    override fun onDestroy() {
        super.onDestroy()
        viewMode.destory()
    }

}