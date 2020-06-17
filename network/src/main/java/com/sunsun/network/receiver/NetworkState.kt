package com.sunsun.network.receiver

class NetworkState {

    var type = 0

    var isConnected = false

    fun NetworkState(type: Int, isConnected: Boolean) {
        this.type = type
        this.isConnected = isConnected
    }
}