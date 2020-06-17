package com.sunsun.tools.basemvvm.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.sunsun.tools.basemvvm.viewmodel.BaseViewModel

abstract class BaseMVVMActivity<V : BaseViewModel, D : ViewDataBinding>() : BaseActivity() {

    lateinit var viewMode: V
    lateinit var dataBinding: D
    abstract fun obtainViewModel(): V
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMode = obtainViewModel()
        dataBinding = DataBindingUtil.bind(mContentView)!!
        dataBinding.setLifecycleOwner(this)
    }

}