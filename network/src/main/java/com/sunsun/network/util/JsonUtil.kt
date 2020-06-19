package com.sunsun.network.util

import android.util.Log
import com.google.gson.Gson
import java.lang.reflect.Type

class JsonUtil {

    private var mGSon: Gson? = null

    companion object {
        val instance: JsonUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            JsonUtil()
        }
    }

    fun getGSon(): Gson? {
        if (mGSon == null) {
            mGSon = Gson()
        }
        return mGSon
    }


    fun parseObjToJsonStr(srcObj: Any?): String? {
        var result = ""
        try {
            result = getGSon()?.toJson(srcObj).toString()
        } catch (e: Exception) {
            Log.getStackTraceString(e)
        }
        return result
    }

    fun <T> parseJsonStrToObj(src: String?, classOfT: Class<T>?): T? {
        return parseJsonStrToObj(src, classOfT, null)
    }

    fun <T> parseJsonStrToObj(
        src: String?,
        classOfT: Class<T>?,
        type: Type?
    ): T? {
        var result: T? = null
        try {
            result = if (type != null) {
                getGSon()?.fromJson(src, type)
            } else {
                getGSon()?.fromJson(src, classOfT)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("NetworkTemplate", Log.getStackTraceString(e))
        }
        return result
    }


    fun <T> parseJsonStrToObj(src: String?, type: Type?): T? {
        var result: T? = null
        try {
            result = getGSon()?.fromJson(src, type)
        } catch (e: Exception) {
            Log.getStackTraceString(e)
        }
        return result
    }

}