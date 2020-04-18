package com.egecius.architecturesdemo.androidarch.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egecius.architecturesdemo.androidarch.AndroidArchViewModel
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCarsMapper
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AndroidArchViewModelFactory(
    private val carsRepository: CarsRepo,
    private val uiCarsMapper: UiCarsMapper,
    private val navigator: Navigator
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST") // taken from Google sample
        return AndroidArchViewModel(carsRepository, uiCarsMapper, navigator, Dispatchers.IO) as T
    }
}
