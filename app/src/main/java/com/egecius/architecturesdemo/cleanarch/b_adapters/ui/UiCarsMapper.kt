package com.egecius.architecturesdemo.cleanarch.b_adapters.ui

import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import com.egecius.architecturesdemo.cleanarch.shared.AllOpen
import javax.inject.Inject

@AllOpen
class UiCarsMapper @Inject constructor() {

    fun toUiCars(carsList: List<Car>): List<UiCar> {
        return mutableListOf<UiCar>().apply {
            carsList.forEach {
                add(UiCar(it.name, it.img))
            }
        }
    }
}