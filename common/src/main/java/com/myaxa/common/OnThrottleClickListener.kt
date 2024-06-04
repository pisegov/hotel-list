package com.myaxa.common

import android.os.SystemClock
import android.view.View

// https://gist.github.com/matpag/1d24841533944dd9fca5fb61e534ca17
abstract class OnThrottleClickListener @JvmOverloads constructor(
    private val minClickInterval: Long = 600 //default value for minclick time
) : View.OnClickListener {

    /**
     * The time the last click was applied
     */
    private var mLastClickTime: Long = 0

    /**
     * @param v The view that was clicked.
     */
    abstract fun onSingleClick(v: View?)

    override fun onClick(v: View?) {
        val currentClickTime: Long = SystemClock.elapsedRealtime()
        val elapsedTime = currentClickTime - mLastClickTime
        if (elapsedTime <= minClickInterval) return
        mLastClickTime = currentClickTime
        onSingleClick(v)
    }
}