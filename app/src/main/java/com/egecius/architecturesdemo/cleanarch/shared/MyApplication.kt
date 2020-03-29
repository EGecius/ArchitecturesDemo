@file:Suppress("unused")

package com.egecius.architecturesdemo.cleanarch.shared

import android.app.Application
import com.egecius.architecturesdemo.cleanarch.a_frameworks.retrofit.MockWebSeverInitializer
import com.egecius.architecturesdemo.cleanarch.shared.di.AppComponent
import com.egecius.architecturesdemo.cleanarch.shared.di.AppModule
import com.egecius.architecturesdemo.cleanarch.shared.di.DaggerAppComponent

class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    private val mockWebSeverInitializer = MockWebSeverInitializer()

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
        mockWebSeverInitializer.init()
    }

    private fun initDependencyInjection() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        appComponent.injectInto(this)
    }
}