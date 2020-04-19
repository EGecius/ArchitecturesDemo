package com.egecius.architecturesdemo.androidarch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarClick
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCar
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCarsMapper
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import com.egecius.architecturesdemo.shared.emptyHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class AndroidArchViewModel constructor(
    private val carsRepository: CarsRepo,
    private val uiCarsMapper: UiCarsMapper,
    private val navigator: Navigator,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val isError = MutableLiveData(false)
    val isFetching = MutableLiveData(false)
    var carsList = MutableLiveData<List<UiCar>>()

    fun onCreate() {
        fetchCarsList()
    }

    private fun fetchCarsList() {
        viewModelScope.launch(dispatcher + emptyHandler) {
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
