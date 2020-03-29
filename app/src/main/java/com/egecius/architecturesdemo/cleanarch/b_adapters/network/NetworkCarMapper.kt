package com.egecius.architecturesdemo.cleanarch.b_adapters.network

import com.egecius.architecturesdemo.cleanarch.d_domain.Car

class NetworkCarMapper {

    fun toCars(jsonList: List<JsonCar>): List<Car> {
        return mutableListOf<Car>().apply {
            jsonList.forEach {
                add(Car(it.name, it.img))
            }
        }
    }
}