package com.egecius.architecturesdemo.androidarch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCar
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCarsMapper
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import com.egecius.architecturesdemo.shared.emptyHandler
import kotlinx.coroutines.launch

class AndroidArchViewModel(
    private val carsRepository: CarsRepo,
    private val uiCarsMapper: UiCarsMapper
) : ViewModel() {

    val isUpdating = MutableLiveData(false)
    val isError = MutableLiveData(false)

    val carsList = MutableLiveData<List<UiCar>>()

    init {
        fetchCars()
    }

    private fun fetchCars() {
        viewModelScope.launch(emptyHandler) {
            isUpdating.value = true
            val cars = carsRepository.getCars()
            carsList.value = uiCarsMapper.toUiCars(cars)
            isUpdating.value = false
        }.invokeOnCompletion {
            it?.let {
                isError.value = true
            }
            isUpdating.value = false
        }
    }

    fun retryFetching() {
        fetchCars()
    }
}