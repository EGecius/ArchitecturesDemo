package com.egecius.architecturesdemo.cleanarch.b_adapters

import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarClick
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import com.egecius.architecturesdemo.cleanarch.c_usecases.GetCarsInteractor
import com.egecius.architecturesdemo.cleanarch.shared.InteractorSchedulers
import io.reactivex.disposables.CompositeDisposable

class CleanArcActivityPresenter(
    private val navigator: Navigator,
    private val getCarsInteractor: GetCarsInteractor,
    private val interactorSchedulers: InteractorSchedulers
) {

    private val compositeDisposable = CompositeDisposable()

    fun onStart() {
        TODO("not implemented")
    }

    fun onStop() {
        compositeDisposable.clear()
    }


    fun onClick(carClick: CarClick) {
        TODO("not implemented")
    }

}
