package com.egecius.architecturesdemo.cleanarch.b_adapters

import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarClick
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import io.reactivex.disposables.CompositeDisposable

class CleanArcActivityPresenter(private val navigator: Navigator) {

    private val compositeDisposable = CompositeDisposable()

    fun onStart() {
        TODO("not implemented")
    }

    fun onStop() {
        TODO("not implemented")
    }


    fun onClick(carClick: CarClick) {
        TODO("not implemented")
    }

}
