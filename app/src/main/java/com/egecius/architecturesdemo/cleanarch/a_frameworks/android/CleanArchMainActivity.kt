package com.egecius.architecturesdemo.cleanarch.a_frameworks.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.egecius.architecturesdemo.R
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.CleanArcActivityPresenter
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCar
import com.egecius.architecturesdemo.cleanarch.di.CleanArchMainActivityModule
import com.egecius.architecturesdemo.cleanarch.di.DaggerCleanArchMainActivityComponent
import com.egecius.architecturesdemo.cleanarch.shared.setGone
import com.egecius.architecturesdemo.cleanarch.shared.setVisible
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
        setupRecycler()
        injectDependencies()
    }

    private fun setupRecycler() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = carRecyclerViewAdapter
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
        showCarListOnly()
        carRecyclerViewAdapter.setData(uiCarsList)
    }

    private fun showCarListOnly() {
        recycler_view.setVisible()
        progress_bar.setGone()
    }

    override fun showErrorMsg() {
        Snackbar.make(parent_layout, getString(R.string.loading_error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) {
                presenter.retryShowingCars()
            }
            .show()
    }
}
