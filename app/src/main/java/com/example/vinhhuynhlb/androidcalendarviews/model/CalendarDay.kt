package com.example.vinhhuynhlb.androidcalendarviews.model

/**
 * Copyright © 2016 AsianTech inc.
 * Created by vinh.huynh on 9/18/17.
 */
data class CalendarDay(var isToDay: Boolean, val toDay: Int,
                       val isOnThisMonth: Boolean, val isHasTask: Boolean, val task: String)
