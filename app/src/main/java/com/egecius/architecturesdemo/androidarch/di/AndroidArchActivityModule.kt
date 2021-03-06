package com.egecius.architecturesdemo.androidarch.di

import androidx.lifecycle.ViewModelProvider
import com.egecius.architecturesdemo.androidarch.AndroidArchActivity
import com.egecius.architecturesdemo.androidarch.AndroidArchViewModel
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCarsMapper
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import dagger.Module
import dagger.Provides

@Module
class AndroidArchActivityModule(private val androidArchActivity: AndroidArchActivity) {

    @Provides
    fun provideAndroidArchViewModel(androidArchViewModelFactory: AndroidArchViewModelFactory): AndroidArchViewModel {
        return ViewModelProvider(androidArchActivity, androidArchViewModelFactory).get(AndroidArchViewModel::class.java)
    }

    @Provides
    fun provideMainActivityViewModelFactory(
        carsRepository: CarsRepo,
        uiCarsMapper: UiCarsMapper,
        navigator: Navigator
    ): AndroidArchViewModelFactory {
        return AndroidArchViewModelFactory(carsRepository, uiCarsMapper, navigator)
    }

    @Provides
    fun provideNavigator(): Navigator {
        return Navigator(androidArchActivity)
    }
}
