package com.egecius.architecturesdemo.cleanarch.shared.di

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

//    fun plus(homeActivityModule: HomeActivityModule): HomeActivityComponent
}
