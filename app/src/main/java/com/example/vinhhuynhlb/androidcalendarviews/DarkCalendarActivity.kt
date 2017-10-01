package com.example.vinhhuynhlb.androidcalendarviews

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.vinhhuynhlb.androidcalendarviews.adapter.BasicDarkCalendarAdapter
import com.example.vinhhuynhlb.androidcalendarviews.common.Constant
import kotlinx.android.synthetic.main.activity_dark_calendar.*
import org.jetbrains.anko.intentFor

/**
 * Copyright © 2016 AsianTech inc.
 * Created by vinh.huynh on 9/19/17.
 */
class DarkCalendarActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dark_calendar)
        unSwipeViewPager.offscreenPageLimit = 1
        val adapter = BasicDarkCalendarAdapter(supportFragmentManager)
        unSwipeViewPager.adapter = adapter
        unSwipeViewPager.currentItem = Constant.MAX_YEAR / 2

        unSwipeViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (adapter.getItem(position) is DarkCalendarFragment) {
                    tvMonthDark.text = (adapter.getItem(position) as DarkCalendarFragment).getMonth(position)
                }
            }
        })
        val position = unSwipeViewPager.currentItem
        if (adapter.getItem(position) is DarkCalendarFragment) {
            tvMonthDark.text = (adapter.getItem(position) as DarkCalendarFragment).getMonth(position)
        }

        imgToLeft.setOnClickListener {
            unSwipeViewPager.setCurrentItem(unSwipeViewPager.currentItem - 1, true)
        }

        imgToRight.setOnClickListener {
            unSwipeViewPager.setCurrentItem(unSwipeViewPager.currentItem + 1, true)
        }
        tvViewStatic.setOnClickListener {
            val intent: Intent? = intentFor<StaticActivity>()
            startActivity(intent)
        }
    }

}