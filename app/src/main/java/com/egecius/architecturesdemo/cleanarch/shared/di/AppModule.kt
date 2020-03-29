package com.egecius.architecturesdemo.cleanarch.shared.di

import android.content.Context
import com.egecius.architecturesdemo.cleanarch.a_frameworks.retrofit.RetrofitAdapter
import com.egecius.architecturesdemo.cleanarch.a_frameworks.room.CarsDatabase
import com.egecius.architecturesdemo.cleanarch.b_adapters.network.CarsRepoImpl
import com.egecius.architecturesdemo.cleanarch.b_adapters.network.JsonCarMapper
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import com.egecius.architecturesdemo.cleanarch.shared.AndroidInteractorSchedulers
import com.egecius.architecturesdemo.cleanarch.shared.InteractorSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideCarsRepository(jsonCarMapper: JsonCarMapper): CarsRepo {
        val carsRetrofitService = RetrofitAdapter().setupRetrofit()
        val carDao = CarsDatabase.getInstance(context).carDao()
        return CarsRepoImpl(carsRetrofitService, carDao, jsonCarMapper)
    }

    @Provides
    fun provideSchedulers(): InteractorSchedulers {
        return AndroidInteractorSchedulers()
    }

}
