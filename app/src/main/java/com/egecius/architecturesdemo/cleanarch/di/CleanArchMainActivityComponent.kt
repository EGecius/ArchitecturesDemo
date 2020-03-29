package com.egecius.architecturesdemo.cleanarch.di

import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CleanArchMainActivity
import dagger.Component

@Component(modules = [CleanArchMainActivityModule::class])
interface CleanArchMainActivityComponent {

    fun injectInto(activity: CleanArchMainActivity)
}
