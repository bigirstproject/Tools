//package com.sunsun.tools.basemvvm.bean
//
//import java.io.Serializable
//
//open class BaseReq : Serializable {
//
//    /**
//     * @category 手机类型
//     */
//    var phoneType: String? = null
//
//    /**
//     * @category 系统版本，ios或android
//     */
//    var ostype = "android"
//
//    /**
//     * @category 客户端系统版本
//     */
//    var systemVersion: String? = null
//
//    /**
//     * @category 是否root
//     */
//    var isHacked: Boolean = false
//
//    /**
//     * @category CID
//     */
//    var cellId: String? = null
//
//    /**
//     * @category SSID
//     */
//    var ssId: String? = null
//
//    /**
//     * @category BSSID
//     */
//    var bssId: String? = null
//
//    /**
//     * @category mac
//     */
//    var mac: String? = null
//
//    /**
//     * @category 经度
//     */
//    var longitude: String? = null
//
//    /**
//     * @category 纬度
//     */
//    var latitude: String? = null
//
//    /**
//     * @category IP
//     */
//    var ip: String? = null
//
//    /**
//     * @category 网络类型
//     */
//    var netState: String? = null
//
//    /**
//     * @category 渠道号
//     */
//    open var channel: String? = null
//
//    /**
//     * @category 手机分辨率
//     */
//    var phoneResolution: String? = null
//
//    /**
//     * @category 客户端版本
//     */
//    var version: String? = null
//
//    /**
//     * @category 授权令牌
//     */
//    var accessToken: String? = null
//
//    /**
//     * @category 追查业务流水
//     */
//    var serialNo: String? = null
//
//    /**
//     * imei
//     */
//    var imei: String? = null
//
//    /**
//     * imsi
//     */
//    var imsi: String? = null
//
//}