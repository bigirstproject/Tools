package com.sunsun.tools.demo.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.sunsun.basemvvm.basemvvm.ui.BaseMVVMActivity
import com.sunsun.tools.R
import com.sunsun.tools.databinding.ActivityLoginBinding
import com.sunsun.tools.demo.bean.CodeLoginReq
import com.sunsun.tools.demo.viewmodel.impl.LoginViewModel

class LoginActivity : BaseMVVMActivity<LoginViewModel, ActivityLoginBinding>() {

    var TAG: String? = LoginActivity::class.simpleName


    override fun layoutID(): Int {
        return R.layout.activity_login
    }

    override fun obtainViewModel(): LoginViewModel {
        return LoginViewModel(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var codeLoginReq = CodeLoginReq(
            "18665051225",
            "123",
            "123",
            grantType = 123,
            subChannel = "vivo",
            deviceType = 1
        )
        viewMode.loginByVerifyCode(codeLoginReq)
        viewMode.ResultLiveData?.observe(this, Observer {
            Log.d(TAG, "" + it)
            Toast.makeText(LoginActivity@ this, it?.toString(), Toast.LENGTH_SHORT).show()
        })
        viewMode.errorResultLiveData?.observe(this, Observer {
            Toast.makeText(LoginActivity@ this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewMode.destory()
    }

}