package com.egecius.architecturesdemo.androidarch.di

import dagger.Subcomponent

@Subcomponent(modules = [AndroidArchActivityModule::class])
interface AndroidArchActivityComponent {

    fun injectInto(activity: AndroidArchActivity)
}
