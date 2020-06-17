package com.sunsun.network.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.sunsun.network.NetworkUtilKotlin

class NetworkStateChangeReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null) {
            if (ConnectivityManager.CONNECTIVITY_ACTION == intent.action) {
                NetworkUtilKotlin.refreshIsConnect("NetworkStateChangeReceiver")
            }
        }
    }
}