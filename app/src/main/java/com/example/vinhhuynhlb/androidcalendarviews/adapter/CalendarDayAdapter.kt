package com.example.vinhhuynhlb.androidcalendarviews.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.vinhhuynhlb.androidcalendarviews.R
import com.example.vinhhuynhlb.androidcalendarviews.model.CalendarDay
import com.example.vinhhuynhlb.androidcalendarviews.util.ScreenUtils
import kotlinx.android.synthetic.main.item_calendar_day.view.*
import org.jetbrains.anko.textColor

/**
 * Copyright © 2016 AsianTech inc.
 * Created by vinh.huynh on 9/18/17.
 */
class CalendarDayAdapter(private val calendarDays: ArrayList<CalendarDay>, private val context: Context) :
        RecyclerView.Adapter<CalendarDayAdapter.DayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DayViewHolder =
            DayViewHolder(LayoutInflater.from(context).inflate(R.layout.item_calendar_day, parent, false))

    override fun onBindViewHolder(holder: DayViewHolder?, position: Int) {
        holder?.init(calendarDays[position])
    }

    override fun getItemCount(): Int = calendarDays.size

    inner class DayViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun init(calendar: CalendarDay) {
            itemView.lnDay.layoutParams.height = ScreenUtils().getWidthScreen(context) / 7
            itemView.tvDay.text = calendar.toDay.toString()
            itemView.lnDay.isSelected = calendar.isToDay
            if (calendar.isHasTask) {
                itemView.tvRedPoint.visibility = View.VISIBLE
            } else {
                itemView.tvRedPoint.visibility = View.INVISIBLE
            }

            if (calendar.isOnThisMonth) {
                itemView.tvDay.textColor = ContextCompat.getColor(context, R.color.black)
            } else {
                itemView.tvDay.textColor = ContextCompat.getColor(context, R.color.darker_gray)
            }

            itemView.lnDay.setOnClickListener {
                if (calendar.isOnThisMonth) {
                    Toast.makeText(context, calendar.toDay.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}