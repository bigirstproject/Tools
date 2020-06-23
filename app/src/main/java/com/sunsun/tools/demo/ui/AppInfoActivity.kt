package com.sunsun.tools.demo.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.sunsun.basemvvm.basemvvm.ui.BaseMVVMActivity
import com.sunsun.tools.R
import com.sunsun.tools.databinding.ActivityAppinfoBinding
import com.sunsun.tools.demo.bean.AppInfoReq
import com.sunsun.tools.demo.viewmodel.impl.AppInfoViewModel
import kotlinx.android.synthetic.main.activity_appinfo.*

class AppInfoActivity : BaseMVVMActivity<AppInfoViewModel, ActivityAppinfoBinding>() {

    var TAG: String? = AppInfoActivity::class.simpleName


    override fun layoutID(): Int {
        return R.layout.activity_appinfo
    }

    override fun obtainViewModel(): AppInfoViewModel {
        return AppInfoViewModel(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var appInfoReq = AppInfoReq(
            "18665051225"
        )
        viewMode.getConfigInfo(appInfoReq)
        viewMode.ResultLiveData?.observe(this, Observer {
//            Log.d(TAG, it?.toString())
            Toast.makeText(LoginActivity@ this, it?.toString(), Toast.LENGTH_SHORT).show()
            app_info.setText(it?.toString())
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