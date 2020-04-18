package com.egecius.architecturesdemo.cleanarch.a_frameworks.android

import android.app.Activity
import com.egecius.architecturesdemo.cleanarch.shared.AllOpen

@AllOpen
class Navigator constructor(private val activity: Activity) {

    fun openDetailScreen(onClick: CarClick) {
        CarDetailActivity.start(activity, onClick)
    }
}