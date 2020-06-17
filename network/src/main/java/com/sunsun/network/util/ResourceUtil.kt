package com.sunsun.network.util

import android.R
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.*

object ResourceUtil {

    private var sContext: Context? = null

    fun init(context: Context) {
        sContext = context.applicationContext
    }

    fun getInteger(resId: Int): Int {
        return if (resId <= 0) {
            0
        } else sContext!!.resources.getInteger(resId)
    }

    fun getDimen(resId: Int): Int {
        return if (resId <= 0) {
            0
        } else sContext!!.resources.getDimensionPixelSize(resId)
    }

    fun getBitmap(resId: Int): Bitmap? {
        if (resId <= 0) {
            return null
        }
        val drawable = sContext!!.resources.getDrawable(resId)
        return if (drawable is BitmapDrawable) {
            drawable.bitmap
        } else null
    }

    fun getDrawable(resId: Int): Drawable? {
        return if (resId <= 0) {
            null
        } else sContext!!.resources.getDrawable(resId)
    }

    fun getColor(resId: Int): Int {
        return if (resId <= 0) {
            0
        } else sContext!!.resources.getColor(resId)
    }

    fun getColorStateList(resId: Int): ColorStateList? {
        return if (resId <= 0) {
            null
        } else sContext!!.resources.getColorStateList(resId)
    }

    fun getString(resId: Int): String? {
        return if (resId <= 0) {
            null
        } else sContext!!.resources.getString(resId)
    }

    fun getColorDrawableStateList(
        normalColorResId: Int,
        pressedColorResId: Int
    ): StateListDrawable? {
        val normalColor = getColor(normalColorResId)
        val pressedColor = getColor(pressedColorResId)
        return createColorDrawableStateList(normalColor, pressedColor)
    }

    fun createColorDrawableStateList(
        normalColor: Int,
        pressedColor: Int
    ): StateListDrawable? {
        val dr =
            StateListDrawable()
        dr.addState(intArrayOf(R.attr.state_pressed), ColorDrawable(pressedColor))
        dr.addState(intArrayOf(R.attr.state_checked), ColorDrawable(pressedColor))
        dr.addState(intArrayOf(), ColorDrawable(normalColor))
        return dr
    }

    fun getDrawableStateList(
        normalResId: Int,
        pressedResId: Int
    ): StateListDrawable? {
        val normal = getDrawable(normalResId)
        val pressed = getDrawable(pressedResId)
        val dr =
            StateListDrawable()
        dr.addState(intArrayOf(R.attr.state_pressed), pressed)
        dr.addState(intArrayOf(R.attr.state_checked), pressed)
        dr.addState(intArrayOf(), normal)
        return dr
    }

    fun createGradientDrawable(startColor: Int, midColor: Int, endColor: Int): Drawable? {
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(startColor, midColor, endColor)
        )
        gradientDrawable.gradientType = GradientDrawable.LINEAR_GRADIENT
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        return gradientDrawable
    }

    fun interpolateColor(colorA: Int, colorB: Int, bias: Float): Int {
        val hsvColorA = FloatArray(3)
        Color.colorToHSV(colorA, hsvColorA)
        val hsvColorB = FloatArray(3)
        Color.colorToHSV(colorB, hsvColorB)
        hsvColorB[0] = interpolate(hsvColorA[0], hsvColorB[0], bias)
        hsvColorB[1] = interpolate(hsvColorA[1], hsvColorB[1], bias)
        hsvColorB[2] = interpolate(hsvColorA[2], hsvColorB[2], bias)
        return Color.HSVToColor(hsvColorB)
    }

    private fun interpolate(
        a: Float,
        b: Float,
        bias: Float
    ): Float {
        return a + (b - a) * bias
    }

}