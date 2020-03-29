package com.egecius.architecturesdemo.cleanarch.shared.di

import com.egecius.architecturesdemo.androidarch.di.AndroidArchActivityComponent
import com.egecius.architecturesdemo.androidarch.di.AndroidArchActivityModule
import com.egecius.architecturesdemo.cleanarch.di.CleanArchMainActivityComponent
import com.egecius.architecturesdemo.cleanarch.di.CleanArchMainActivityModule
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

    fun plus(module: CleanArchMainActivityModule): CleanArchMainActivityComponent
}
