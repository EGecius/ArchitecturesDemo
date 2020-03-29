package com.egecius.architecturesdemo.cleanarch.di

import com.egecius.architecturesdemo.cleanarch.CleanArchMainActivity
import dagger.Component

@Component(modules = [CleanArchMainActivityModule::class])
interface CleanArchMainActivityComponent {

    fun injectInto(activity: CleanArchMainActivity)
}
