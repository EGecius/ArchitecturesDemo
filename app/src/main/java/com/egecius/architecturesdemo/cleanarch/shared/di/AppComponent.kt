package com.egecius.architecturesdemo.cleanarch.shared.di

import com.egecius.architecturesdemo.androidarch.di.AndroidArchActivityComponent
import com.egecius.architecturesdemo.androidarch.di.AndroidArchActivityModule
import com.egecius.architecturesdemo.cleanarch.di.CleanArchActivityComponent
import com.egecius.architecturesdemo.cleanarch.di.CleanArcActivityModule
import com.egecius.architecturesdemo.cleanarch.shared.MyApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    fun injectInto(psApplication: MyApplication)

    fun plus(module: AndroidArchActivityModule): AndroidArchActivityComponent

    fun plus(module: CleanArcActivityModule): CleanArchActivityComponent
}
