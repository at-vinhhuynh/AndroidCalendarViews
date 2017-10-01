package com.example.vinhhuynhlb.androidcalendarviews.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.vinhhuynhlb.androidcalendarviews.DarkCalendarFragment
import com.example.vinhhuynhlb.androidcalendarviews.common.Constant

/**
 * Copyright © 2016 AsianTech inc.
 * Created by vinh.huynh on 9/18/17.
 */
open class BasicDarkCalendarAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = DarkCalendarFragment().newInstance(position)

    override fun getCount(): Int = Constant.MAX_YEAR
}