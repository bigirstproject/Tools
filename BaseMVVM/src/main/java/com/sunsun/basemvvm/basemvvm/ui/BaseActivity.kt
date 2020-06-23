package com.sunsun.basemvvm.basemvvm.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.sunsun.basemvvm.R
import kotlinx.android.synthetic.main.common_base_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

abstract class BaseActivity : BaseAnalysisActivity() {

    abstract fun layoutID(): Int
    lateinit var mContentView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.common_base_layout)
        setupContentView()
        EventBus.getDefault().register(this)
    }

    fun setupContentView() {
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        )
        mContentView = View.inflate(this, layoutID(), null)
        root_layout.addView(mContentView, layoutParams)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    @Subscribe
    open fun onEventMainThread(event: Any) {

    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

}