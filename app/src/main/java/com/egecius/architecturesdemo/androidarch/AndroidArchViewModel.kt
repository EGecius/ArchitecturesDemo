package com.egecius.architecturesdemo.androidarch

import androidx.lifecycle.LiveData
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
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    // TODO: 18/04/2020 remove it, once tests for  AndroidArchViewModel are finished
    val liveDataDemo = MutableLiveData(1)
    val isError = MutableLiveData(false)

    var carsList = fetchCars(dispatcher)

    private fun fetchCars(dispatcher: CoroutineDispatcher): LiveData<List<UiCar>> {
        return liveData(dispatcher) {
            try {
                val cars = carsRepository.getCars()
                uiCarsMapper.toUiCars(cars)
                isError.value = false
            } catch (e: Exception) {
                isError.value = true
            }
        }
    }

    fun onCarClick(carClick: CarClick) {
        navigator.openDetailScreen(carClick)
    }

    fun onClickedRetry() {
        isError.value = false
        carsList = fetchCars(dispatcher)
    }
}
