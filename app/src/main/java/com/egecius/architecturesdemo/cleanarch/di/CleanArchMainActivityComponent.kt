package com.egecius.architecturesdemo.cleanarch.di

import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CleanArchActivity
import dagger.Subcomponent

@Subcomponent(modules = [CleanArchMainActivityModule::class])
interface CleanArchMainActivityComponent {

    fun injectInto(activity: CleanArchActivity)
}
