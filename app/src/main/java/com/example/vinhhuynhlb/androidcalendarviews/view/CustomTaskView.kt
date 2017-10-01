package com.example.vinhhuynhlb.androidcalendarviews.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.support.v4.content.ContextCompat
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.vinhhuynhlb.androidcalendarviews.R
import com.example.vinhhuynhlb.androidcalendarviews.model.TriAngPos
import com.example.vinhhuynhlb.androidcalendarviews.util.ScreenUtils

/**
 * Copyright © 2016 AsianTech inc.
 * Created by vinh.huynh on 9/19/17.
 */
class CustomTaskView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    enum class FlagV {
        BOTTOM_LEFT,
        BOTTOM_CENTER,
        BOTTOM_RIGHT,
        TOP_LEFT,
        TOP_CENTER,
        TOP_RIGHT
    }

    private val mPaintRect: Paint = Paint()
    private val mPaintStroke: Paint = Paint()
    private val mPainText: TextPaint = TextPaint()
    private val mRectTop: RectF = RectF(0f, 30f, ScreenUtils().getWidthScreen(context) / 7 * 3f, ScreenUtils().getWidthScreen(context) / 7 * 2f)
    private val mRectBot: RectF = RectF(0f, 0f, ScreenUtils().getWidthScreen(context) / 7 * 3f, ScreenUtils().getWidthScreen(context) / 7 * 2f)

    private val mRadius = 10f
    private val mPainTriAng: Paint = Paint()
    private val mTriAngDistance = 25f
    private val mTriAngHeight = 30f
    private var mSize = 0f
    private var mPathTriAng: Path = Path()
    private var mFlagVParent: FlagV = FlagV.BOTTOM_CENTER
    private var mShouldDraw: Boolean = false
    private var mTask: String = ""

    init {
        mSize = ScreenUtils().getWidthScreen(context) / 7f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mShouldDraw) {
            mShouldDraw = false
            drawTriAng(canvas, mFlagVParent)
        }
    }

    private fun drawTriAng(canvas: Canvas, flagV: FlagV) {
        mFlagVParent = flagV
        mPainTriAng.color = ContextCompat.getColor(context, R.color.calendar_mon_default_background)
        mPainTriAng.strokeWidth = 25f
        mPainTriAng.style = Paint.Style.FILL
        mPathTriAng.reset()
        when (flagV) {
            FlagV.BOTTOM_LEFT -> {
                mPathTriAng.moveTo(mSize / 2f - mTriAngDistance, mSize * 2f)
                mPathTriAng.lineTo(mSize / 2f, mSize * 2f + mTriAngHeight)
                mPathTriAng.lineTo(mSize / 2f + mTriAngDistance, mSize * 2f)
            }
            FlagV.BOTTOM_RIGHT -> {
                mPathTriAng.moveTo((2 * mSize + (mSize / 2)) - mTriAngDistance, mSize * 2f)
                mPathTriAng.lineTo((2f * mSize + (mSize / 2)), mSize * 2f + mTriAngHeight)
                mPathTriAng.lineTo((2 * mSize + (mSize / 2)) + mTriAngDistance, mSize * 2f)
            }
            FlagV.BOTTOM_CENTER -> {
                mPathTriAng.moveTo((mSize + (mSize / 2)) - mTriAngDistance, mSize * 2f)
                mPathTriAng.lineTo((mSize + (mSize / 2f)), mSize * 2f + mTriAngHeight)
                mPathTriAng.lineTo((mSize + (mSize / 2)) + mTriAngDistance, mSize * 2f)
            }
            FlagV.TOP_LEFT -> {
                mPathTriAng.moveTo(mSize / 2f - mTriAngDistance, 30f)
                mPathTriAng.lineTo(mSize / 2f, 0f)
                mPathTriAng.lineTo(mSize / 2f + mTriAngDistance, 30f)
            }
            FlagV.TOP_RIGHT -> {
                mPathTriAng.moveTo((2 * mSize + (mSize / 2)) - mTriAngDistance, 30f)
                mPathTriAng.lineTo((2f * mSize + (mSize / 2)), 0f)
                mPathTriAng.lineTo((2 * mSize + (mSize / 2)) + mTriAngDistance, 30f)
            }
            FlagV.TOP_CENTER -> {
                mPathTriAng.moveTo((mSize + (mSize / 2)) - mTriAngDistance, 30f)
                mPathTriAng.lineTo((mSize + (mSize / 2f)), 0f)
                mPathTriAng.lineTo((mSize + (mSize / 2)) + mTriAngDistance, 30f)
            }
        }
        mPathTriAng.close()
        canvas.drawPath(mPathTriAng, mPainTriAng)
        // Fill
        mPaintRect.style = Paint.Style.FILL
        mPaintRect.color = ContextCompat.getColor(context, R.color.calendar_mon_default_background)
        // Stroke -> Same Color | Optional
        mPaintStroke.strokeWidth = 5f
        mPaintStroke.style = Paint.Style.STROKE
        mPaintStroke.color = ContextCompat.getColor(context, R.color.calendar_mon_default_background)

        if (mFlagVParent == FlagV.BOTTOM_CENTER || mFlagVParent == FlagV.BOTTOM_LEFT || mFlagVParent == FlagV.BOTTOM_RIGHT) {
            canvas.drawRoundRect(mRectBot, mRadius, mRadius, mPaintRect)
            canvas.drawRoundRect(mRectBot, mRadius, mRadius, mPaintStroke)
        } else {
            canvas.drawRoundRect(mRectTop, mRadius, mRadius, mPaintRect)
            canvas.drawRoundRect(mRectTop, mRadius, mRadius, mPaintStroke)
        }
        // Text
        drawText(mTask, canvas)
    }

    fun drawTaskView(flagV: FlagV, pos: Int, task: String) {
        mTask = task
        mFlagVParent = flagV
        mShouldDraw = true
        invalidate()
        x = getYItemTask(pos).x
        y = getYItemTask(pos).y
    }

    fun resetView() {
        mShouldDraw = false
        invalidate()
    }

    private fun drawText(text: String, canvas: Canvas) {
        mPainText.color = ContextCompat.getColor(context, R.color.task_text_color)
        mPainText.style = Paint.Style.FILL_AND_STROKE
        mPainText.strokeWidth = 1f
        mPainText.textSize = 30f
        mPainText.isAntiAlias = true
        mPainText.isDither = true
        mPainText.textAlign = Paint.Align.CENTER
        canvas.drawText(text, 0, text.length, mSize + mSize / 2, mSize + 15f, mPainText)
    }

    private fun getYItemTask(pos: Int): TriAngPos {
        val triAngPos = TriAngPos(0f, 0f)
        when (pos) {
            in 0..6 -> {
                when (pos) {
                    0 -> {
                        triAngPos.x = 0f
                        triAngPos.y = mSize - mTriAngHeight
                    }
                    6 -> {
                        triAngPos.x = mSize * 4f
                        triAngPos.y = mSize - mTriAngHeight
                    }
                    else -> {
                        triAngPos.x = mSize * (pos - 1)
                        triAngPos.y = mSize - mTriAngHeight
                    }
                }
            }
            in 7..13 -> {
                when (pos) {
                    7 -> {
                        triAngPos.x = 0f
                        triAngPos.y = 2 * mSize - mTriAngHeight
                    }
                    13 -> {
                        triAngPos.x = mSize * 4f
                        triAngPos.y = 2 * mSize - mTriAngHeight
                    }
                    else -> {
                        triAngPos.x = mSize * (pos - 8)
                        triAngPos.y = 2 * mSize - mTriAngHeight
                    }
                }
            }
            in 14..20 -> {
                when (pos) {
                    14 -> {
                        triAngPos.x = 0f
                        triAngPos.y = 0f
                    }
                    20 -> {
                        triAngPos.x = mSize * 4f
                        triAngPos.y = 0f
                    }
                    else -> {
                        triAngPos.x = mSize * (pos - 15)
                        triAngPos.y = 0f
                    }
                }
            }
            in 21..27 -> {
                when (pos) {
                    21 -> {
                        triAngPos.x = 0f
                        triAngPos.y = mSize
                    }
                    27 -> {
                        triAngPos.x = mSize * 4f
                        triAngPos.y = mSize
                    }
                    else -> {
                        triAngPos.x = mSize * (pos - 22)
                        triAngPos.y = mSize
                    }
                }
            }
            in 28..34 -> {
                when (pos) {
                    28 -> {
                        triAngPos.x = 0f
                        triAngPos.y = 2 * mSize
                    }
                    34 -> {
                        triAngPos.x = mSize * 4f
                        triAngPos.y = 2 * mSize
                    }
                    else -> {
                        triAngPos.x = mSize * (pos - 29)
                        triAngPos.y = 2 * mSize
                    }
                }
            }
            in 35..41 -> {
                when (pos) {
                    35 -> {
                        triAngPos.x = 0f
                        triAngPos.y = 3 * mSize
                    }
                    41 -> {
                        triAngPos.x = mSize * 4f
                        triAngPos.y = 3 * mSize
                    }
                    else -> {
                        triAngPos.x = mSize * (pos - 36)
                        triAngPos.y = 3 * mSize
                    }
                }
            }
        }
        return triAngPos
    }
}
