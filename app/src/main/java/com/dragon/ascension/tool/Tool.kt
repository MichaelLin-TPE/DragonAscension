package com.dragon.ascension.tool

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.view.WindowManager
import androidx.annotation.RequiresApi

object Tool {

    fun Context.getScreenWidth(): Int {
        val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val windowMetrics = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            windowManager.currentWindowMetrics
        } else {
            return Resources.getSystem().displayMetrics.widthPixels
        }
        val bounds = windowMetrics.bounds
        return bounds.width()
    }

    fun Context.dpToPx(dp:Int):Int{
        return (dp * this.resources.displayMetrics.density).toInt()
    }


}