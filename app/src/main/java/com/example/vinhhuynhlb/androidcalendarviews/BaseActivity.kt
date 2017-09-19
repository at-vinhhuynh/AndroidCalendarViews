package com.example.vinhhuynhlb.androidcalendarviews

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

/**
 * Copyright © 2016 AsianTech inc.
 * Created by vinh.huynh on 9/18/17.
 */
open class BaseActivity : AppCompatActivity() {
    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val frTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            frTransaction.addToBackStack(fragment.tag)
            // Animation Option On Used Here
        }
        frTransaction.replace(R.id.container, fragment)
        frTransaction.commit()
    }
}
