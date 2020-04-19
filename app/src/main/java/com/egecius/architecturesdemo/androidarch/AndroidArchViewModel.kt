package com.egecius.architecturesdemo.androidarch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarClick
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCar
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCarsMapper
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import kotlinx.coroutines.launch

class AndroidArchViewModel(
    private val carsRepository: CarsRepo,
    private val uiCarsMapper: UiCarsMapper,
    private val navigator: Navigator
) : ViewModel() {

    val isError = MutableLiveData(false)
    val isFetching = MutableLiveData(false)
    var carsList = MutableLiveData<List<UiCar>>()

    fun onCreate() {
        fetchCarsList()
    }

    private fun fetchCarsList() {
        viewModelScope.launch {
            isFetching.value = true
            carsList.value = uiCarsMapper.toUiCars(carsRepository.getCars())
        }.invokeOnCompletion {
            it?.let { isError.value = true }
            isFetching.value = false
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
