package com.egecius.architecturesdemo.androidarch

import androidx.lifecycle.ViewModel
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCarsMapper
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo

class AndroidArchViewModel(
    carsRepository: CarsRepo,
    uiCarsMapper: UiCarsMapper
) : ViewModel() {

}
