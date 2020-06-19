package com.sunsun.tools

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.sunsun.network.template.OKHttpFactory

class ToolsApplication() : Application() {

    companion object {
        lateinit var mInstance: ToolsApplication
    }

    override fun onCreate() {
        super.onCreate()
        if (!tryCheckProcess(applicationContext)) {
            return
        }
        mInstance = this
        OKHttpFactory.instance.init(mInstance)

    }

    /**
     * 防止初始化两次
     *
     * @return
     */
    private fun tryCheckProcess(context: Context): Boolean {
        val pid = android.os.Process.myPid()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activityManager.runningAppProcesses.any {
            it.pid == pid && it.processName.equals(
                BuildConfig.APPLICATION_ID,
                ignoreCase = true
            )
        }
    }


}