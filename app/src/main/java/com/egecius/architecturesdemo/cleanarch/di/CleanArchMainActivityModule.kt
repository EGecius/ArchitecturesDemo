package com.egecius.architecturesdemo.cleanarch.di

import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CleanArchMainActivity
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import com.egecius.architecturesdemo.cleanarch.a_frameworks.retrofit.RetrofitAdapter
import com.egecius.architecturesdemo.cleanarch.a_frameworks.room.CarsDatabase
import com.egecius.architecturesdemo.cleanarch.b_adapters.network.CarsRepoImpl
import com.egecius.architecturesdemo.cleanarch.b_adapters.network.JsonCarMapper
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.CleanArcActivityPresenter
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCarsMapper
import com.egecius.architecturesdemo.cleanarch.c_usecases.GetCarsInteractor
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import com.egecius.architecturesdemo.cleanarch.shared.AndroidInteractorSchedulers
import com.egecius.architecturesdemo.cleanarch.shared.InteractorSchedulers
import dagger.Module
import dagger.Provides

@Module
class CleanArchMainActivityModule(private val cleanArchMainActivity: CleanArchMainActivity) {

    @Provides
    fun provideCleanArcActivityPresenter(
        navigator: Navigator,
        interactorSchedulers: InteractorSchedulers,
        getCarsInteractor: GetCarsInteractor,
        uiCarsMapper: UiCarsMapper
    ): CleanArcActivityPresenter {
        return CleanArcActivityPresenter(
            navigator,
            getCarsInteractor,
            uiCarsMapper,
            interactorSchedulers
        )
    }

    @Provides
    fun provideNavigator(): Navigator {
        return Navigator(cleanArchMainActivity)
    }
}
