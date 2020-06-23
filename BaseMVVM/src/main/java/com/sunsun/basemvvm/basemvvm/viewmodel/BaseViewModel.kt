package com.sunsun.basemvvm.basemvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    abstract fun initData()

    abstract fun destory()

}