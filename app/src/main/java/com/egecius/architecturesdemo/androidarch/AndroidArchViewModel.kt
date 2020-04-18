package com.egecius.architecturesdemo.androidarch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarClick
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCar
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCarsMapper
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import kotlinx.coroutines.CoroutineDispatcher

class AndroidArchViewModel constructor(
    private val carsRepository: CarsRepo,
    private val uiCarsMapper: UiCarsMapper,
    private val navigator: Navigator,
    dispatcher: CoroutineDispatcher
) : ViewModel() {

    // TODO: 18/04/2020 remove it, once tests for  AndroidArchViewModel are finished
    val liveDataDemo = MutableLiveData(1)

    val carsList = liveData<List<UiCar>>(dispatcher) {
        val cars = carsRepository.getCars()
        uiCarsMapper.toUiCars(cars)
    }

    fun onCarClick(carClick: CarClick) {
        navigator.openDetailScreen(carClick)
    }

}
