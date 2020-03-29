package com.egecius.architecturesdemo.cleanarch.di

import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CleanArchMainActivity
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import com.egecius.architecturesdemo.cleanarch.a_frameworks.retrofit.RetrofitAdapter
import com.egecius.architecturesdemo.cleanarch.a_frameworks.room.CarsDatabase
import com.egecius.architecturesdemo.cleanarch.b_adapters.CarsRepoImpl
import com.egecius.architecturesdemo.cleanarch.b_adapters.CleanArcActivityPresenter
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import com.egecius.architecturesdemo.cleanarch.shared.AndroidSchedulers
import com.egecius.architecturesdemo.cleanarch.shared.Schedulers
import dagger.Module
import dagger.Provides

@Module
class CleanArchMainActivityModule(private val cleanArchMainActivity: CleanArchMainActivity) {

    @Provides
    fun provideCarsRepository(): CarsRepo {
        val carsRetrofitService = RetrofitAdapter().setupRetrofit()
        val carDao = CarsDatabase.getInstance(cleanArchMainActivity).carDao()
        return CarsRepoImpl(carsRetrofitService, carDao)
    }

    @Provides
    fun provideSchedulers(): Schedulers {
        return AndroidSchedulers()
    }

    @Provides
    fun provideCleanArcActivityPresenter(navigator: Navigator): CleanArcActivityPresenter {
        return CleanArcActivityPresenter(navigator)
    }

    @Provides
    fun provideNavigator(): Navigator {
        return Navigator(cleanArchMainActivity)
    }
}
