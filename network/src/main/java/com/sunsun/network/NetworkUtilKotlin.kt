package com.sunsun.network

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.net.*
import androidx.annotation.RequiresPermission
import com.sunsun.network.util.CommonUtil

/**
 * 网络状态
 */
data class NetworkInfoKotlin(var refreshSuccess: Boolean = false) {
    var isConnect: Boolean = false
}


object NetworkUtilKotlin {

    val NETWORK_TYPE_WIFI = "wifi"
    val NETWORK_TYPE_NONE = "no_network"
    val NETWORK_TYPE_UNKNOWN = "unknown"

    val NETWORK_CLASS_NO_NETWORK = "-1"
    val NETWORK_CLASS_UNKNOWN = "0"
    val NETWORK_CLASS_2G = "2G"
    val NETWORK_CLASS_2_5G = "2.5G"
    val NETWORK_CLASS_2_75G = "2.75G"
    val NETWORK_CLASS_3G = "3G"
    val NETWORK_CLASS_4G = "4G"

    //接入点类型
    val NETWORK_AP_TYPE_MOBILE_WAP = 0     //cmwap
    val NETWORK_AP_TYPE_MOBILE_NET = 1     //cmnet
    val NETWORK_AP_TYPE_WIFI = 2         //wifi
    val NETWORK_AP_TYPE_NOT_CONFIRM = 99   //not confirm

    // 这部分在低版本的SDK中没有定义,但高版本有定义,
    // 由于取值不与其它常量不冲突,因此直接在这里定义
    private val NETWORK_TYPE_EVDO_B = 12
    private val NETWORK_TYPE_EHRPD = 14
    private val NETWORK_TYPE_HSPAP = 15
    private val NETWORK_TYPE_LTE = 13

    private var sCacheActiveNetworkInitial = false
    private var sCacheActiveNetwork: NetworkInfo? = null

    private val mConnectivityManager by lazy {
        CommonUtil.getApp().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private val networkInfo: NetworkInfoKotlin =
        NetworkInfoKotlin(false)

    @SuppressLint("MissingPermission")
    fun isConnect(): Boolean {
        //没有刷新成功就直接去拿
        if (!networkInfo.refreshSuccess) {
//            Log.i("cwtnetwork", "拿取失败，返回直接拿取值")
            return getActiveNetworkInfo()?.isConnected ?: false
        }
        //刷新成功就拿当前值
//        Log.i("cwtnetwork", "拿取成功，返回刷新值")
        return networkInfo.isConnect
    }

    @SuppressLint("MissingPermission")
    @TargetApi(21)
    fun registerNetworkListener() {
//            Log.i("cwtnetwork", "增加网络状态监听器")
        val builder = NetworkRequest.Builder()
        val request = builder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        mConnectivityManager.registerNetworkCallback(
            request,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network?) {
                    super.onAvailable(network)
//                    Log.i("cwtnetwork", "有网了")
                    refreshIsConnect(
                        "registerNetworkListener"
                    )
                }

                override fun onUnavailable() {
                    super.onUnavailable()
//                    Log.i("cwtnetwork", "没网了1")
                    refreshIsConnect(
                        "registerNetworkListener"
                    )
                }

                override fun onLost(network: Network?) {
                    super.onLost(network)
//                    Log.i("cwtnetwork", "没网了2")
                    refreshIsConnect(
                        "registerNetworkListener"
                    )
                }
            })
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    private fun getActiveNetworkInfo(): NetworkInfo? {
        return mConnectivityManager.activeNetworkInfo
    }

    @SuppressLint("MissingPermission")
    @JvmOverloads
    fun refreshIsConnect(from: String = "null") {
        try {
            val isConnect = getActiveNetworkInfo()?.isConnected ?: false
            networkInfo.refreshSuccess = true
            networkInfo.isConnect = isConnect
//            Log.i("cwtnetwork", ", 刷新成功,当前网络连通状态：$isConnect;来自$from")
        } catch (e: Exception) {
//            Log.i("cwtnetwork", ", 刷新失败;来自$from")
            networkInfo.refreshSuccess = false
        }
    }
}

