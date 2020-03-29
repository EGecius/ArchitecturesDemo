package com.egecius.architecturesdemo.cleanarch.c_usecases

import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import io.reactivex.Single
import javax.inject.Inject

class GetCarsInteractor @Inject constructor(private val carsRepo: CarsRepo) {

    fun getCarsSingle(): Single<List<Car>> {
        return carsRepo.getCarsSingle()
    }
}