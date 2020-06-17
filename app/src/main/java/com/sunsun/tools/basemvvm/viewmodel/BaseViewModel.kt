package com.sunsun.tools.basemvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    open fun initData() {}

    open fun destory() {}

}