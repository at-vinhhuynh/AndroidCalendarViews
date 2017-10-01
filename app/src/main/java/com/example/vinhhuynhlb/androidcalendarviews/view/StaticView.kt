package com.example.vinhhuynhlb.androidcalendarviews.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.example.vinhhuynhlb.androidcalendarviews.R

/**
 * Copyright © 2016 AsianTech inc.
 * Created by vinh.huynh on 9/28/17.
 */
class StaticView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    companion object {
        private val LINE_SPACE = 160f
        private val LINE_MARGIN = 4f
    }

    private val mPainLine = Paint()

    init {
        mPainLine.color = ContextCompat.getColor(context, R.color.static_line)
        mPainLine.strokeWidth = 3f
        mPainLine.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawLines(canvas)
    }

    private fun drawLines(canvas: Canvas) {
        for (i in 0..10) {
            if (i == 0) {
                canvas.drawLine(0f, i * LINE_SPACE + 1.5f, width.toFloat(), i * LINE_SPACE + 1.5f, mPainLine)
            }
            canvas.drawLine(0f, i * LINE_SPACE, width.toFloat(), i * LINE_SPACE, mPainLine)
        }
    }
}