package com.egecius.architecturesdemo.cleanarch.a_frameworks.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.egecius.architecturesdemo.R
import com.egecius.architecturesdemo.cleanarch.b_adapters.CleanArcActivityPresenter
import com.egecius.architecturesdemo.cleanarch.b_adapters.UiCar
import com.egecius.architecturesdemo.cleanarch.di.CleanArchMainActivityModule
import com.egecius.architecturesdemo.cleanarch.di.DaggerCleanArchMainActivityComponent
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_clean_arch.*
import javax.inject.Inject

class CleanArchMainActivity : AppCompatActivity(), CleanArcActivityPresenter.View {

    @Inject
    lateinit var presenter: CleanArcActivityPresenter

    private val carRecyclerViewAdapter = CarRecyclerViewAdapter(object : OnCarClickListener {
        override fun onClick(carClick: CarClick) {
            presenter.onClick(carClick)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clean_arch)
        injectDependencies()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    private fun injectDependencies() {
        DaggerCleanArchMainActivityComponent.builder()
            .cleanArchMainActivityModule(CleanArchMainActivityModule(this))
            .build().injectInto(this)
    }

    override fun showCars(uiCarsList: List<UiCar>) {
        carRecyclerViewAdapter.setData(uiCarsList)
    }

    override fun showErrorMsg() {
        Snackbar.make(parent_layout, getString(R.string.loading_error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) {
                presenter.retryShowingCars()
            }
            .show()
    }
}
