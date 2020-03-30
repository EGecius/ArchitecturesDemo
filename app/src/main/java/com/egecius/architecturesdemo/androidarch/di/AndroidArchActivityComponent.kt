package com.egecius.architecturesdemo.androidarch.di

import com.egecius.architecturesdemo.androidarch.AndroidArchActivity
import dagger.Subcomponent

@Subcomponent(modules = [AndroidArchActivityModule::class])
interface AndroidArchActivityComponent {

    fun injectInto(activity: AndroidArchActivity)
}
