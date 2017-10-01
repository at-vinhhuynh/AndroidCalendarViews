package com.example.vinhhuynhlb.androidcalendarviews

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vinhhuynhlb.androidcalendarviews.adapter.CalendarDayAdapter
import com.example.vinhhuynhlb.androidcalendarviews.common.Constant
import com.example.vinhhuynhlb.androidcalendarviews.model.CalendarDay
import kotlinx.android.synthetic.main.fragment_calendar.*
import org.joda.time.DateTime
import java.util.*

/**
 * Copyright © 2016 AsianTech inc.
 * Created by vinh.huynh on 9/18/17.
 */
class CalendarFragment : Fragment() {
    private var calendarDays: ArrayList<CalendarDay> = ArrayList()
    private var position: Int = 0

    companion object {
        private const val NUM_COLUMN = 7
        private const val KEY_POSITION: String = "KEY_POSITION"
    }

    fun newInstance(pos: Int): CalendarFragment {
        val fragment = CalendarFragment()
        val bundle = Bundle()
        bundle.putInt(KEY_POSITION, pos)
        fragment.arguments = bundle
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        position = arguments.getInt(KEY_POSITION)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = GridLayoutManager(context, NUM_COLUMN)
        val calendarAdapter = CalendarDayAdapter(calendarDays, context)
        recyclerView.adapter = calendarAdapter
        initialCalendar(DateTime().plusMonths(getOffSetPage(position)))
        calendarAdapter.notifyDataSetChanged()
    }

    private fun getOffSetPage(pos: Int): Int = pos - Constant.MAX_YEAR / 2

    private fun initialCalendar(date: DateTime) {
        val lastDayOfLastMonth = date.withDayOfMonth(1).dayOfWeek - 1
        val lastDayOfThisMonth = date.dayOfMonth().maximumValue
        val firstDayOfNextMonth = 7 - date.withDayOfMonth(lastDayOfThisMonth).dayOfWeek

        // Add Previous Month's day
        (0 until lastDayOfLastMonth)
                .map { Integer.parseInt(date.withDayOfMonth(1).plusDays(it - lastDayOfLastMonth).toString(DarkCalendarFragment.DATE_FORMAT_DAY)) }
                .map { CalendarDay(false, it, false, getRandomTask(), "") }
                .forEach { calendarDays.add(it) }

        // Add Current Month's day
        for (i: Int in 0 until lastDayOfThisMonth) {
            val day = Integer.parseInt(date.withDayOfMonth(i + 1).toString(DarkCalendarFragment.DATE_FORMAT_DAY))
            // Check is today
            val isToday = date.withDayOfMonth(i + 1).toString(DarkCalendarFragment.DATE_FORMAT_FULL) == DateTime().toString(DarkCalendarFragment.DATE_FORMAT_FULL)
            val calendarDay = CalendarDay(isToday, day, true, getRandomTask(), "")
            calendarDays.add(calendarDay)
        }

        // Add Next Month's day
        (0 until firstDayOfNextMonth)
                .map { Integer.parseInt(date.withDayOfMonth(lastDayOfThisMonth).plusDays(it + 1).toString(DarkCalendarFragment.DATE_FORMAT_DAY)) }
                .map { CalendarDay(false, it, false, getRandomTask(), "") }
                .forEach { calendarDays.add(it) }
    }

    private fun getRandomTask(): Boolean = (Math.random() * 50 + 1).toInt() % 3 == 0

    fun getYear(pos: Int): String = DateTime().plusMonths(getOffSetPage(pos)).toString(DarkCalendarFragment.DATE_FORMAT_YEAR)

    fun getMonth(pos: Int): String =
            DateTime().plusMonths(getOffSetPage(pos)).toString(DarkCalendarFragment.DATE_FORMAT_MONTH)
}
