package com.egecius.architecturesdemo.cleanarch.b_adapters.network

import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import javax.inject.Inject

class JsonCarMapper @Inject constructor() {

    fun toCars(jsonList: List<JsonCar>): List<Car> {
        return mutableListOf<Car>().apply {
            jsonList.forEach {
                add(Car(it.name, it.img))
            }
        }
    }
}