package com.sunsun.network.util

import java.io.IOException
import java.net.SocketTimeoutException

object HttpCode {

    val SUCCESS = 0 //请求成功

    val ERROR_CODE_SESSION_OVERDUE = 7 //session 过期


    val ERROR_NO_NETWORK_CODE = 0 //没有网络

    val ERROR_NO_PARAMS_CODE = 1 //请求参数异常

    val ERROR_NETWORK_CODE = 2 //请求失败

    val ERROR_NET_CODE = 3 //响应失败code

    val ERROR_BUSINESS_CODE = 4 //业务返回的失败

    val BUSINESS_CODE = 5 //业务成功

//    public static final int ERROR_SERVER_CODE = 6;//服务器异常

    //    public static final int ERROR_SERVER_CODE = 6;//服务器异常
    val ERROR_CODE_SYSTEM = -1 //系统异常

    val ERROR_CODE_NETWORK_ERROR = -2 //网络连接断开

    val ERROR_CODE_CONNECT_SERVER_FAILED = -3 //服务器停机不可达

    val ERROR_CODE_UPLOAD = -5 // 上传失败, 非200

    val ERROR_CODE_DOWNLOAD_ERROR = -6 //下载失败

    val ERROR_CODE_ZIP_ERROR = -7 //解压失败

    val ERROR_CODE_REQUESTING_ERROR = -8 //正在请求中

    val ERROR_CODE_DECRYPT_FAILED = -9 //解密失败

    val ERROR_CODE_NULL_BEAN = -50 //业务对象为null

    val ERROR_CODE_REQUEST_URL_IS_EMPTY = -51 // url 为空

    val ERROR_CODE_REQUEST_PARAM = -101 // 请求参数为空

    val ERROR_CODE_REQUEST_PARAM_ENCRYPT = -102 // 请求参数加密后为空

    val ERROR_CODE_RESPONSE_STRING = -200 // 读取response中的数据失败或为空

    val ERROR_CODE_RESPONSE_JSON = -201 // response 解析json失败

    val ERROR_CODE_RESPONSE_ENCRYPT = -202 // response 解密json失败

    val ERROR_CODE_RESPONSE_REPEAT = -203 // response 重复请求

    val ERROR_CODE_GET_TAOBAO_URL = -10000 //session 过期

    val ERROR_CODE_PHONE_FREQUENT_REQUESTS = 120061 //号码频繁请求

    val ERROR_CODE_PHONE_HAVE_REGISTERED = 12003 //手机号已注册

    val USER_LOGIN_LIMIT_JDV1 = 12085 //及贷1.0用户

    val USER_LOGIN_LIMIT_WH = 12086 //PP分期 PP好借 闪银

    val ERROR_CODE_ALERT = 12094 //系统关闭注册&登录 弹dialog

    val ERROR_CODE_LOAD = 12095 //系统关闭注册&登录 弹WebView

    //活体检测
    val LIVE_BODY_UN_MOD = 12092 //用户人脸识别信息，当前为不能修改状态

    val LIVE_BODY_CHECK_FAIL = 12090 //活体检测被拒绝

    val LIVE_BODY_OVER_FAIL_COUNT = 12088 //活体检测超过错误次数

    val MSG_TIMEOUT = "网络连接超时, 请稍后重试"
    val MSG_SYSTEM = "网络异常，请重试"
    val MSG_DATA_PARSE = "数据异常，请重试"
    private val ERROR_CODE_TIMEOUT = -4 // 连接超时


    /**
     * 根据请求失败异常, 细分各种异常code
     *
     * @param e exception
     * @return code
     */
    fun getExceptionCode(e: IOException?): Int {
        var code: Int = ERROR_CODE_SYSTEM
        if (e is SocketTimeoutException) {
            code = ERROR_CODE_TIMEOUT
        }
        return code
    }

    /**
     * 根据code 转译msg
     *
     * @param code
     * @param msg
     * @return
     */
    fun convertMsgByCode(code: Int, msg: String?): String? {
        var converted = msg
        if (code == ERROR_CODE_TIMEOUT) {
            converted = MSG_TIMEOUT
        } else if (code == ERROR_CODE_SYSTEM) {
            converted = MSG_SYSTEM
        }
        return converted
    }
}