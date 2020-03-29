package com.egecius.architecturesdemo.cleanarch.b_adapters.network

import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import io.reactivex.Single

class CarsRepoImpl(
    private val networkService: NetworkService,
    private val carDao: CarDao
) : CarsRepo {

    override fun getCarsSingle(): Single<List<Car>> {
        // TODO: 29/03/2020 add cashing
        return networkService.getCarsFullSingle()
    }

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