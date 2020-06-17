package com.sunsun.network.util

import java.math.BigDecimal

object FormatUtil {

    /**
     * 格式化单位
     *
     * @param size cache size
     * @return size str
     */
    fun formatCacheSize(size: Double): String? {
        val kiloByte = size / 1024
        if (kiloByte < 1) {
            return "0K"
        }
        val mByte = kiloByte / 1024
        if (mByte < 1) {
            val result1 =
                BigDecimal(java.lang.Double.toString(kiloByte))
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB"
        }
        val gByte = mByte / 1024
        if (gByte < 1) {
            val result2 =
                BigDecimal(java.lang.Double.toString(mByte))
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB"
        }
        val tBytes = gByte / 1024
        if (tBytes < 1) {
            val result3 =
                BigDecimal(java.lang.Double.toString(gByte))
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB"
        }
        val result4 = BigDecimal.valueOf(tBytes)
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB"
    }


    fun stringToJSON(strJson: String): String? {
        // 计数tab的个数
        var tabNum = 0
        val jsonFormat = StringBuilder()
        val length = strJson.length
        var last = 0.toChar()
        for (i in 0 until length) {
            val c = strJson[i]
            if (c == '{') {
                tabNum++
                jsonFormat.append(c).append("\n")
                jsonFormat.append(getSpaceOrTab(tabNum))
            } else if (c == '}') {
                tabNum--
                jsonFormat.append("\n")
                jsonFormat.append(getSpaceOrTab(tabNum))
                jsonFormat.append(c)
            } else if (c == ',') {
                jsonFormat.append(c).append("\n")
                jsonFormat.append(getSpaceOrTab(tabNum))
            } else if (c == ':') {
                jsonFormat.append(c).append(" ")
            } else if (c == '[') {
                tabNum++
                val next = strJson[i + 1]
                if (next == ']') {
                    jsonFormat.append(c)
                } else {
                    jsonFormat.append(c).append("\n")
                    jsonFormat.append(getSpaceOrTab(tabNum))
                }
            } else if (c == ']') {
                tabNum--
                if (last == '[') {
                    jsonFormat.append(c)
                } else {
                    jsonFormat.append("\n").append(getSpaceOrTab(tabNum)).append(c)
                }
            } else {
                jsonFormat.append(c)
            }
            last = c
        }
        return jsonFormat.toString()
    }

    private fun getSpaceOrTab(tabNum: Int): String? {
        val sbTab = StringBuilder()
        for (i in 0 until tabNum) {
            sbTab.append('\t')
        }
        return sbTab.toString()
    }

}