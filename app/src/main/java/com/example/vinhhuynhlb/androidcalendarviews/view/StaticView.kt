package com.example.vinhhuynhlb.androidcalendarviews.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.example.vinhhuynhlb.androidcalendarviews.R
import com.example.vinhhuynhlb.androidcalendarviews.model.StaticPoint
import com.example.vinhhuynhlb.androidcalendarviews.util.ScreenUtils
import java.util.*

/**
 * Copyright © 2016 AsianTech inc.
 * Created by vinh.huynh on 9/28/17.
 */
class StaticView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    companion object {
        private const val LINE_SPACE = 100f
        private const val NORMAL_POINT_RADIUS = 6f
        private const val BIGGEST_POINT_RADIUS = 10f
    }

    private val mPainLine = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPainChart = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPathChart = Path()
    private var mSpaceY = 0f
    private var mPoints = ArrayList<StaticPoint>()
    private val mNormalPointPaint = Paint()
    private val mBiggestPointPaint = Paint()

    init {
        mSpaceY = (ScreenUtils().getWidthScreen(context) - ScreenUtils().convertDpToPixel(50f, context)) / 9
        // Init StaticPoint
        initStaticPoint()
        // PointStatic Paint
        mNormalPointPaint.color = ContextCompat.getColor(context, R.color.white)
        mNormalPointPaint.style = Paint.Style.FILL
        mBiggestPointPaint.color = ContextCompat.getColor(context, R.color.yellow)
        mBiggestPointPaint.style = Paint.Style.FILL
        // Horizontal Line
        mPainLine.color = ContextCompat.getColor(context, R.color.static_line)
        mPainLine.strokeWidth = 3f
        mPainLine.style = Paint.Style.FILL
        // Chart Process Line
        mPainChart.color = ContextCompat.getColor(context, R.color.static_chard_process)
        mPainChart.strokeWidth = 6f
        mPainChart.style = Paint.Style.STROKE
        mPainChart.strokeJoin = Paint.Join.ROUND
        mPainChart.strokeCap = Paint.Cap.ROUND
        mPainChart.pathEffect = CornerPathEffect(20f)
        mPainChart.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawLines(canvas)
        drawStaticPoint(canvas)
        drawChartProcess(canvas)
    }

    private fun drawLines(canvas: Canvas) {
        for (i in 0..10) {
            if (i == 0) {
                canvas.drawLine(0f, i * LINE_SPACE + BIGGEST_POINT_RADIUS, width.toFloat(), i * LINE_SPACE + BIGGEST_POINT_RADIUS, mPainLine)
            } else {
                canvas.drawLine(0f, i * LINE_SPACE, width.toFloat(), i * LINE_SPACE, mPainLine)
            }
        }
    }

    private fun drawChartProcess(canvas: Canvas) {
        mPathChart.reset()
        mPathChart.moveTo(mPoints[0].x, mPoints[0].y)
        mPoints.forEach { point ->
            mPathChart.lineTo(point.x, point.y)
        }
        canvas.drawPath(mPathChart, mPainChart)
    }


    private fun drawStaticPoint(canvas: Canvas) {
        val minY = getMaxY(mPoints)
        mPoints.forEach { point ->
            if (point.y == minY) {
                canvas.drawCircle(point.x, point.y, BIGGEST_POINT_RADIUS, mBiggestPointPaint)
            } else {
                canvas.drawCircle(point.x, point.y, NORMAL_POINT_RADIUS, mNormalPointPaint)
            }
        }
    }

    private fun initStaticPoint() {
        (0..7)
                .asSequence()
                .map { StaticPoint((7 - it) * mSpaceY + BIGGEST_POINT_RADIUS, getRandomY() * mSpaceY + BIGGEST_POINT_RADIUS) }
                .forEach {
                    mPoints.add(it)
                }
    }

    private fun getMaxY(points: ArrayList<StaticPoint>): Float {
        val staticPoint = points.minBy { point ->
            point.y
        }
        return staticPoint!!.y
    }

    private fun getRandomY(): Int = Random().nextInt((8) + 1)

}
