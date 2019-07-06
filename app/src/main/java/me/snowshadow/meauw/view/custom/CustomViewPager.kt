package me.snowshadow.meauw.view.custom

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
import java.lang.reflect.Field


class CustomViewPager : ViewPager {

    constructor(context: Context) : super(context) {
        ini()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        ini()
    }

    private fun ini() {
        val mFlingDistance: Field = ViewPager::class.java.getDeclaredField("mFlingDistance")
        mFlingDistance.isAccessible = true
        mFlingDistance.set(this, 30)

    }

//    override fun onTouchEvent(event: MotionEvent): Boolean = shouldSwipe

}