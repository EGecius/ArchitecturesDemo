package com.egecius.architecturesdemo.cleanarch.b_adapters

import com.egecius.architecturesdemo.cleanarch.a_frameworks.retrofit.NetworkService
import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo

class CarsRepoImpl(
    private val networkService: NetworkService,
    private val carDao: CarDao
) : CarsRepo {

    override suspend fun getCars(): List<Car> {
        val carsFull = networkService.getCarsFull()
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