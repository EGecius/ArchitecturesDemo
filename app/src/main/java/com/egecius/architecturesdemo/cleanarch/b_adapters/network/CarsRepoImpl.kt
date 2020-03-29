package com.egecius.architecturesdemo.cleanarch.b_adapters.network

import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import io.reactivex.Single

class CarsRepoImpl(
    private val networkService: NetworkService,
    private val carDao: CarDao,
    private val jsonCarMapper: JsonCarMapper
) : CarsRepo {

    override fun getCarsSingle(): Single<List<Car>> {
        // TODO: 29/03/2020 add cashing
        return networkService.getCarsFullSingle().map { jsonCarMapper.toCars(it) }
    }

    override suspend fun getCars(): List<Car> {
        val jsonCars = networkService.getCarsFull()
        val cars = jsonCarMapper.toCars(jsonCars)
        storeCarsInDatabase(cars)
        return returnInternetOrDbData(cars)
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