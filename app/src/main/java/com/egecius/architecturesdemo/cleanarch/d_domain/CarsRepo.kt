package com.egecius.architecturesdemo.cleanarch.d_domain

import io.reactivex.Single

interface CarsRepo {

    fun getCarsSingle() : Single<List<Car>>

    suspend fun getCars(): List<Car>
}