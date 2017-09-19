package com.example.vinhhuynhlb.androidcalendarviews.util

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Copyright © 2016 AsianTech inc.
 * Created by vinh.huynh on 9/18/17.
 */
open class ScreenUtils {
    fun getWidthScreen(context: Context): Int {
        val wm = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dimension = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dimension)
        return dimension.widthPixels
    }
}
