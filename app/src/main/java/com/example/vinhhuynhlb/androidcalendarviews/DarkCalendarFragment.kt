package com.example.vinhhuynhlb.androidcalendarviews

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vinhhuynhlb.androidcalendarviews.adapter.DarkCalendarDayAdapter
import com.example.vinhhuynhlb.androidcalendarviews.common.Constant
import com.example.vinhhuynhlb.androidcalendarviews.model.CalendarDay
import com.example.vinhhuynhlb.androidcalendarviews.view.CustomTaskView
import kotlinx.android.synthetic.main.fragment_dark_calendar.*
import org.joda.time.DateTime
import java.util.*


/**
 * Copyright © 2016 AsianTech inc.
 * Created by vinh.huynh on 9/18/17.
 */
class DarkCalendarFragment : Fragment() {
    companion object {
        private const val NUM_COLUMN = 7
        private const val KEY_POSITION: String = "KEY_POSITION"
        const val DATE_FORMAT_FULL = "MM/dd/YYYY"
        const val DATE_FORMAT_MONTH = "MMMM"
        const val DATE_FORMAT_YEAR = "YYYY"
        const val DATE_FORMAT_DAY = "dd"
    }

    private var calendarDays: ArrayList<CalendarDay> = ArrayList()
    private var position: Int = 0
    private var mPositionClicked: Int = -1

    fun newInstance(pos: Int): DarkCalendarFragment {
        val fragment = DarkCalendarFragment()
        val bundle = Bundle()
        bundle.putInt(KEY_POSITION, pos)
        fragment.arguments = bundle
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_dark_calendar, container, false)
        position = arguments.getInt(KEY_POSITION)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = GridLayoutManager(context, NUM_COLUMN)
        val calendarAdapter = DarkCalendarDayAdapter(calendarDays, context)
        recyclerView.adapter = calendarAdapter

        val verticalDecoration = DividerItemDecoration(context,
                DividerItemDecoration.HORIZONTAL)
        val verticalDivider = ContextCompat.getDrawable(activity, R.drawable.vertical_divider)
        verticalDecoration.setDrawable(verticalDivider)
        recyclerView.addItemDecoration(verticalDecoration)

        val horizontalDecoration = DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        val horizontalDivider = ContextCompat.getDrawable(activity, R.drawable.horizontal_divider)
        horizontalDecoration.setDrawable(horizontalDivider)
        recyclerView.addItemDecoration(horizontalDecoration)

        initialCalendar(DateTime().plusMonths(getOffSetPage(position)))
        calendarAdapter.notifyDataSetChanged()
        calendarAdapter.onItemClick = { position ->
            if (mPositionClicked == position) {
                mPositionClicked = -1
                customTaskView.resetView()
            } else {
                if (position < calendarDays.size && position != -1) {
                    customTaskView.resetView()
                    customTaskView.drawTaskView(getFlagTriAngWithPosition(position), position, calendarDays[position].task)
                    for (calendar in calendarDays) {
                        calendar.isToDay = false
                    }
                    calendarDays[position].isToDay = true
                    mPositionClicked = position
                    calendarAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun getOffSetPage(pos: Int): Int = pos - Constant.MAX_YEAR / 2

    private fun initialCalendar(date: DateTime) {
        val lastDayOfLastMonth = date.withDayOfMonth(1).dayOfWeek - 1
        val lastDayOfThisMonth = date.dayOfMonth().maximumValue
        val firstDayOfNextMonth = 7 - date.withDayOfMonth(lastDayOfThisMonth).dayOfWeek

        // Add Previous Month's day
        (0 until lastDayOfLastMonth).forEach { i: Int ->
            val day = Integer.parseInt(date.withDayOfMonth(1).plusDays(i - lastDayOfLastMonth).toString(DATE_FORMAT_DAY))
            val calendarDay = CalendarDay(false, day, false, getRandomTask(), "Meeting At 15:00")
            calendarDays.add(calendarDay)
        }

        // Add Current Month's day
        for (i: Int in 0 until lastDayOfThisMonth) {
            val day = Integer.parseInt(date.withDayOfMonth(i + 1).toString(DATE_FORMAT_DAY))
            // Check is today
            val isToday = date.withDayOfMonth(i + 1).toString(DATE_FORMAT_FULL) == DateTime().toString(DATE_FORMAT_FULL)
            val calendarDay = CalendarDay(isToday, day, true, getRandomTask(), "Meeting At 15:00")
            calendarDays.add(calendarDay)
        }

        // Add Next Month's day
        (0 until firstDayOfNextMonth)
                .asSequence()
                .map { Integer.parseInt(date.withDayOfMonth(lastDayOfThisMonth).plusDays(it + 1).toString(DATE_FORMAT_DAY)) }
                .map { CalendarDay(false, it, false, getRandomTask(), "Meeting At 15:00") }
                .forEach { calendarDays.add(it) }
    }

    private fun getRandomTask(): Boolean = (Math.random() * 50 + 1).toInt() % 3 == 0

    fun getMonth(pos: Int): String =
            DateTime().plusMonths(getOffSetPage(pos)).toString(DATE_FORMAT_MONTH)

    private fun getFlagTriAngWithPosition(position: Int): CustomTaskView.FlagV {
        when {
            position < 14 -> {
                if (position == 0 || position == 7) {
                    return CustomTaskView.FlagV.TOP_LEFT
                } else if (position == 6 || position == 13) {
                    return CustomTaskView.FlagV.TOP_RIGHT
                }
                return CustomTaskView.FlagV.TOP_CENTER
            }
            (position == 28 || position == 21 || position == 14 || position == 35) -> {
                return CustomTaskView.FlagV.BOTTOM_LEFT
            }

            (position == 20 || position == 27 || position == 34 || position == 41) -> {
                return CustomTaskView.FlagV.BOTTOM_RIGHT
            }
            else -> {
                return CustomTaskView.FlagV.BOTTOM_CENTER
            }
        }
    }
}