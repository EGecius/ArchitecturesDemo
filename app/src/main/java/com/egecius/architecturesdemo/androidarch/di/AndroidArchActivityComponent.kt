package com.egecius.architecturesdemo.androidarch.di

import com.egecius.architecturesdemo.androidarch.AndroidArchActivity
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CleanArchMainActivity
import dagger.Component

@Component(modules = [AndroidArchActivityModule::class])
interface AndroidArchActivityComponent {

    fun injectInto(activity: AndroidArchActivity)
}
