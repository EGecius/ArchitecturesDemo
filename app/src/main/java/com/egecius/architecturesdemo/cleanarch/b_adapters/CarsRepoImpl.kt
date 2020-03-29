package com.egecius.architecturesdemo.cleanarch.b_adapters

import com.egecius.architecturesdemo.cleanarch.a_frameworks.retrofit.CarRetrofitService
import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo

class CarsRepoImpl(
    private val carRetrofitService: CarRetrofitService,
    private val carDao: CarDao
) : CarsRepo {

    override suspend fun getCars(): List<Car> {
        val carsFull = carRetrofitService.getCarsFull()
        storeCarsInDatabase(carsFull)
        return returnInternetOrDbData(carsFull)
    }

    private suspend fun returnInternetOrDbData(dataInternet: List<Car>): List<Car> {
        return if (dataInternet.isEmpty()) {
            carDao.loadAllCars()
        } else {
            dataInternet
        }
    }

    private suspend fun storeCarsInDatabase(cars: List<Car>) {
        for (car in cars) {
            carDao.insertCar(car)
        }
    }
}