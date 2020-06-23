package com.sunsun.tools.demo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.sunsun.basemvvm.basemvvm.ui.BaseActivity
import com.sunsun.tools.R
import com.sunsun.tools.demo.ui.AppInfoActivity
import com.sunsun.tools.demo.ui.LoginActivity


class MainActivity : BaseActivity() {

    lateinit var button: Button
    override fun layoutID(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(MainActivity@ this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        findViewById<Button>(R.id.app_info).setOnClickListener() {
            val intent = Intent(MainActivity@ this, AppInfoActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}