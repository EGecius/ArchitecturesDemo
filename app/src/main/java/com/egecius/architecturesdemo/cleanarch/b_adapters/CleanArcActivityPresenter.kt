package com.egecius.architecturesdemo.cleanarch.b_adapters

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
                { }
            )

        compositeDisposable.add(disposable)
    }

    fun onStop() {
        compositeDisposable.clear()
    }


    fun onClick(carClick: CarClick) {
        TODO("not implemented")
    }

    interface View {
        fun showCars(uiCarsList: List<UiCar>)

    }
}
