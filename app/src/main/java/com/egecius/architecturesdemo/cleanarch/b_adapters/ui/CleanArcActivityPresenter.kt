package com.egecius.architecturesdemo.cleanarch.b_adapters.ui

import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarClick
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import com.egecius.architecturesdemo.cleanarch.c_usecases.GetCarsInteractor
import com.egecius.architecturesdemo.cleanarch.shared.InteractorSchedulers
import com.egecius.architecturesdemo.cleanarch.shared.on
import io.reactivex.disposables.CompositeDisposable

class CleanArcActivityPresenter(
    private val navigator: Navigator,
    private val getCarsInteractor: GetCarsInteractor,
    private val uiMapper: UiCarsMapper,
    private val schedulers: InteractorSchedulers
) {

    private lateinit var view: View
    private val compositeDisposable = CompositeDisposable()

    fun onStart(view: View) {
        this.view = view
        showCarsList()
    }

    private fun showCarsList() {
        val disposable = getCarsInteractor.getCarsSingle()
            .map { uiMapper.toUiCars(it) }
            .on(schedulers)
            .subscribe(
                { view.showCars(it) },
                { view.showErrorMsg() }
            )

        compositeDisposable.add(disposable)
    }

    fun onStop() {
        compositeDisposable.clear()
    }

    fun onClick(carClick: CarClick) {
        navigator.openDetailScreen(carClick)
    }

    fun retryShowingCars() {
        showCarsList()
    }

    interface View {
        fun showCars(uiCarsList: List<UiCar>)
        fun showErrorMsg()

    }
}
