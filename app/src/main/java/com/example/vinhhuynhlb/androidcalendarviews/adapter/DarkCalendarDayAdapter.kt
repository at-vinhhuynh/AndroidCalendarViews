package com.example.vinhhuynhlb.androidcalendarviews.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vinhhuynhlb.androidcalendarviews.R
import com.example.vinhhuynhlb.androidcalendarviews.model.CalendarDay
import com.example.vinhhuynhlb.androidcalendarviews.util.ScreenUtils
import kotlinx.android.synthetic.main.item_dark_calendar_day.view.*
import org.jetbrains.anko.textColor

/**
 * Copyright © 2016 AsianTech inc.
 * Created by vinh.huynh on 9/18/17.
 */
class DarkCalendarDayAdapter(private val calendarDays: ArrayList<CalendarDay>, private val context: Context) :
        RecyclerView.Adapter<DarkCalendarDayAdapter.DayViewHolder>() {

    var onItemClick: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DayViewHolder =
            DayViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dark_calendar_day, parent, false))

    override fun onBindViewHolder(holder: DayViewHolder?, position: Int) {
        holder?.init(calendarDays[position])
    }

    override fun getItemCount(): Int = calendarDays.size

    inner class DayViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun init(calendar: CalendarDay) {
            itemView.lnDay.layoutParams.height = ScreenUtils().getWidthScreen(context) / 7
            itemView.tvDay.text = calendar.toDay.toString()

            if (calendar.isOnThisMonth) {
                itemView.tvDay.textColor = ContextCompat.getColor(context, R.color.calendar_day_default_text)
            } else {
                itemView.tvDay.textColor = ContextCompat.getColor(context, R.color.text_other_month)
            }
            itemView.lnDay.isSelected = calendar.isToDay

            itemView.lnDay.setOnClickListener {
                if (calendar.isOnThisMonth) {
                    onItemClick(adapterPosition)
                }
            }
        }
    }

}