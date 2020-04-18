package com.egecius.architecturesdemo.androidarch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarClick
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCar
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCarsMapper
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class AndroidArchViewModel constructor(
    private val carsRepository: CarsRepo,
    private val uiCarsMapper: UiCarsMapper,
    private val navigator: Navigator,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    // TODO: 18/04/2020 remove it, once tests for  AndroidArchViewModel are finished
    val liveDataDemo = MutableLiveData(1)
    val isError = MutableLiveData(false)
    var carsList = MutableLiveData<List<UiCar>>()

    init {
        fetchCarsList()
    }

    private fun fetchCarsList() {
        viewModelScope.launch {
            val cars = carsRepository.getCars()
            carsList.value = uiCarsMapper.toUiCars(cars)
        }.invokeOnCompletion {
            it?.let { isError.value = true }
        }
    }

    fun onCarClick(carClick: CarClick) {
        navigator.openDetailScreen(carClick)
    }

    fun onClickedRetry() {
        isError.value = false
        fetchCarsList()
    }
}
