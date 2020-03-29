@file:Suppress("unused")

package com.egecius.architecturesdemo.cleanarch.shared

import android.app.Application
import com.egecius.architecturesdemo.cleanarch.a_frameworks.retrofit.MockWebSeverInitializer

class MyApplication : Application() {

    private val mockWebSeverInitializer = MockWebSeverInitializer()

    override fun onCreate() {
        super.onCreate()
        mockWebSeverInitializer.init()
    }

}