package com.egecius.architecturesdemo.cleanarch.b_adapters.network

import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import io.reactivex.Single
import kotlinx.coroutines.rx2.rxSingle

/** Returns data from network. If data not found there, shows cashed data */
class CarsRepoImpl(
    private val networkService: NetworkService,
    private val carDao: CarDao,
    private val jsonCarMapper: JsonCarMapper
) : CarsRepo {

    /** List of cars for clients that use RxJava */
    override fun getCarsSingle(): Single<List<Car>> {
        return rxSingle {
            getCars()
        }
    }

    /** List of cars for clients that use Coroutines */
    override suspend fun getCars(): List<Car> {
        val jsonCars = networkService.getCarsFull()
        val cars = jsonCarMapper.toCars(jsonCars)
        storeInDb(cars)
        return returnLatest(cars)
    }

    private suspend fun storeInDb(cars: List<Car>) {
        for (car in cars) {
            carDao.insertCar(car)
        }
    }

    private suspend fun returnLatest(dataInternet: List<Car>): List<Car> {
        return if (dataInternet.isEmpty()) {
            carDao.loadAllCars()
        } else {
            dataInternet
        }
    }
}