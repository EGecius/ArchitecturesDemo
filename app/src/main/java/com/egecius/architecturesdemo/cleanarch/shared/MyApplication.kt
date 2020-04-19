@file:Suppress("unused")

package com.egecius.architecturesdemo.cleanarch.shared

import android.app.Application
import android.util.Log
import com.egecius.architecturesdemo.cleanarch.a_frameworks.retrofit.MockWebSeverInitializer
import com.egecius.architecturesdemo.cleanarch.shared.di.AppComponent
import com.egecius.architecturesdemo.cleanarch.shared.di.AppModule
import com.egecius.architecturesdemo.cleanarch.shared.di.DaggerAppComponent
import java.lang.Thread.setDefaultUncaughtExceptionHandler

class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    private val mockWebSeverInitializer = MockWebSeverInitializer()

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
        mockWebSeverInitializer.init()
        setUncaughtExceptionHandler()
    }

    private fun setUncaughtExceptionHandler() {
        // without it set Coroutines will crash the app without any explanation
        setDefaultUncaughtExceptionHandler { _, exception ->
            Log.e("Eg:MyApplication:24", "onCreate() exception: $exception")
        }
    }

    private fun initDependencyInjection() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        appComponent.injectInto(this)
    }
}