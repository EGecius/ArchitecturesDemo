package com.egecius.architecturesdemo.androidarch.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCarsMapper
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo

class AndroidArchViewModelFactory(
    private val carsRepository: CarsRepo,
    private val uiCarsMapper: UiCarsMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST") // taken from Google sample
        return AndroidArchViewModel(carsRepository, uiCarsMapper) as T
    }
}
