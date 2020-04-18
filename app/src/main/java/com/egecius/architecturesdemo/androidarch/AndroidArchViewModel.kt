package com.egecius.architecturesdemo.androidarch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarClick
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCar
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCarsMapper
import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo

class AndroidArchViewModel(
    private val carsRepository: CarsRepo,
    private val uiCarsMapper: UiCarsMapper,
    private val navigator: Navigator
) : ViewModel() {

    fun onCarClick(carClick: CarClick) {
        navigator.openDetailScreen(carClick)
    }

    val carsList = liveData<List<UiCar>> {
        val cars = carsRepository.getCars()
        uiCarsMapper.toUiCars(cars)
    }

}
