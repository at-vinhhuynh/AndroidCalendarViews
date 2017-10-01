package com.example.vinhhuynhlb.androidcalendarviews

import android.app.Activity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_static.*
import org.jetbrains.anko.textColor

/**
 * Copyright © 2016 AsianTech inc.
 * Created by vinh.huynh on 9/28/17.
 */
class StaticActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_static)

        tvFollower.isSelected = true
        tvFollower.setOnClickListener {
            if (!tvFollower.isSelected) {
                tvFollower.isSelected = !tvFollower.isSelected
                tvFollowing.isSelected = false
                tvUpload.isSelected = false
                tvFollowing.textColor = ContextCompat.getColor(this, R.color.dark_white)
                tvUpload.textColor = ContextCompat.getColor(this, R.color.dark_white)
                tvFollower.textColor = ContextCompat.getColor(this, R.color.tab_title_selected_dark)
            }
        }
        tvFollowing.setOnClickListener {
            if (!tvFollowing.isSelected) {
                tvFollowing.isSelected = !tvFollowing.isSelected
                tvFollower.isSelected = false
                tvUpload.isSelected = false
                tvFollower.textColor = ContextCompat.getColor(this, R.color.dark_white)
                tvUpload.textColor = ContextCompat.getColor(this, R.color.dark_white)
                tvFollowing.textColor = ContextCompat.getColor(this, R.color.tab_title_selected_dark)
            }
        }
        tvUpload.setOnClickListener {
            if (!tvUpload.isSelected) {
                tvUpload.isSelected = !tvUpload.isSelected
                tvFollower.isSelected = false
                tvFollowing.isSelected = false
                tvFollower.textColor = ContextCompat.getColor(this, R.color.dark_white)
                tvFollowing.textColor = ContextCompat.getColor(this, R.color.dark_white)
                tvUpload.textColor = ContextCompat.getColor(this, R.color.tab_title_selected_dark)
            }
        }
    }
}
