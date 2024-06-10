package com.myaxa.hotel_details_impl.util

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlin.math.abs

/*
* It refreshes only on vertical scroll
* Needed for proper scroll processing in nested recycler views
*
* Inspired by https://stackoverflow.com/a/34224634
* */
internal class VerticalSwipeRefreshLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : SwipeRefreshLayout(context, attrs) {

    private val touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    private var prevX = 0f
    private var declined = false

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

                val motionEvent = MotionEvent.obtain(event)
                prevX = motionEvent.x
                motionEvent.recycle()

                declined = false // New action
            }

            MotionEvent.ACTION_MOVE -> {
                val eventX = event.x
                val xDiff = abs((eventX - prevX).toDouble()).toFloat()
                if (declined || xDiff > touchSlop) {
                    declined = true // Memorize
                    return false
                }
            }
        }
        return super.onInterceptTouchEvent(event)
    }
}