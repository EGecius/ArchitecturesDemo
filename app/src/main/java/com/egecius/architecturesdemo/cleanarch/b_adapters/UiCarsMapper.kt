package com.egecius.architecturesdemo.cleanarch.b_adapters

import com.egecius.architecturesdemo.cleanarch.d_domain.Car

class UiCarsMapper {

    fun toUiCars(carsList: List<Car>): List<UiCar> {
        return mutableListOf<UiCar>().apply {
            carsList.forEach {
                add(UiCar(it.name, it.img))
            }
        }
    }
}