package com.egecius.architecturesdemo.cleanarch.a_frameworks.android

import android.app.Activity

class Navigator(private val activity: Activity) {

    fun openDetailScreen(onClick: CarClick) {
        CarDetailActivity.start(activity, onClick)
    }
}