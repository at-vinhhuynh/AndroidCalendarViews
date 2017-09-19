package com.example.vinhhuynhlb.androidcalendarviews

import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.vinhhuynhlb.androidcalendarviews.adapter.BasicCalendarAdapter
import com.example.vinhhuynhlb.androidcalendarviews.common.Constant
import com.example.vinhhuynhlb.androidcalendarviews.util.ScreenUtils
import kotlinx.android.synthetic.main.activity_calendar.*

/**
 * Copyright © 2016 AsianTech inc.
 * Created by vinh.huynh on 9/18/17.
 */
class CalendarActivity : BaseActivity() {
    companion object {
        private const val IMAGE_WIDTH: Int = 2560
        private const val IMAGE_HEIGHT: Int = 1600
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        rlTop.layoutParams.width = ScreenUtils().getWidthScreen(this)
        rlTop.layoutParams.height = ScreenUtils().getWidthScreen(this) * IMAGE_HEIGHT / IMAGE_WIDTH
        viewPager.offscreenPageLimit = 1
        val adapter = BasicCalendarAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        viewPager.currentItem = Constant.MAX_YEAR / 2

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (adapter.getItem(position) is CalendarFragment) {
                    tvYear.text = (adapter.getItem(position) as CalendarFragment).getYear(position)
                    tvMonth.text = (adapter.getItem(position) as CalendarFragment).getMonth(position)
                }
            }
        })
        val position = viewPager.currentItem
        if (adapter.getItem(position) is CalendarFragment) {
            tvYear.text = (adapter.getItem(position) as CalendarFragment).getYear(position)
            tvMonth.text = (adapter.getItem(position) as CalendarFragment).getMonth(position)
        }
    }
}