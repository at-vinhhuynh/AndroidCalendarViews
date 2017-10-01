package com.example.vinhhuynhlb.androidcalendarviews.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Copyright © 2016 AsianTech inc.
 * Created by vinh.huynh on 9/19/17.
 */
class UnSwipeViewPager(context: Context,
                       attr: AttributeSet) : ViewPager(context, attr) {

    private var mCanScrollHorizontally: Boolean = false

    override fun canScrollHorizontally(direction: Int): Boolean {
        return if (mCanScrollHorizontally) {
            super.canScrollHorizontally(direction)
        } else false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (mCanScrollHorizontally) {
            super.onInterceptTouchEvent(ev)
        } else false
    }
}
