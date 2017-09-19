package com.example.vinhhuynhlb.androidcalendarviews.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vinhhuynhlb.androidcalendarviews.CalendarFragment
import com.example.vinhhuynhlb.androidcalendarviews.R
import com.example.vinhhuynhlb.androidcalendarviews.adapter.BasicCalendarAdapter
import com.example.vinhhuynhlb.androidcalendarviews.common.Constant
import kotlinx.android.synthetic.main.calendarview.*

/**
 * Copyright © 2016 AsianTech inc.
 * Created by vinh.huynh on 9/19/17.
 */
open class CalendarView : Fragment() {
    var mOnCalendarListener: OnCalendarListener? = null
    open fun setOnCalendarListener(onCalendarListener: OnCalendarListener) {
        mOnCalendarListener = onCalendarListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.calendarview, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.offscreenPageLimit = 1
        val adapter = BasicCalendarAdapter(activity.supportFragmentManager)
        viewPager.adapter = adapter
        viewPager.currentItem = Constant.MAX_YEAR / 2

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (adapter.getItem(position) is CalendarFragment) {
                    mOnCalendarListener?.onUpdateMonth((adapter.getItem(position) as CalendarFragment).getMonth(position))
                    mOnCalendarListener?.onUpdateYear((adapter.getItem(position) as CalendarFragment).getYear(position))
                }
            }
        })
        val position = viewPager.currentItem
        if (adapter.getItem(position) is CalendarFragment) {
            mOnCalendarListener?.onUpdateMonth((adapter.getItem(position) as CalendarFragment).getMonth(position))
            mOnCalendarListener?.onUpdateYear((adapter.getItem(position) as CalendarFragment).getYear(position))
        }
    }

    interface OnCalendarListener {
        fun onUpdateMonth(month: String)
        fun onUpdateYear(year: String)
    }
}
