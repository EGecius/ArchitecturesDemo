package com.egecius.architecturesdemo.androidarch

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.egecius.architecturesdemo.R
import com.egecius.architecturesdemo.androidarch.di.AndroidArchActivityModule
import com.egecius.architecturesdemo.androidarch.di.DaggerAndroidArchActivityComponent
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarClick
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarDetailActivity
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarRecyclerViewAdapter
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.OnCarClickListener
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCar
import com.egecius.architecturesdemo.cleanarch.di.DaggerCleanArchMainActivityComponent
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_android_arch.*

class AndroidArchActivity : AppCompatActivity() {

    private val viewModel: AndroidArchViewModel by viewModels()

    private val carRecyclerViewAdapter = CarRecyclerViewAdapter(object : OnCarClickListener {
        override fun onClick(carClick: CarClick) {
            CarDetailActivity.start(this@AndroidArchActivity, carClick)
        }
    })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_arch)
        injectDependencies()
        setupUi()
    }

    private fun injectDependencies() {
        DaggerAndroidArchActivityComponent.builder()
            .androidArchActivityModule(AndroidArchActivityModule(this))
            .build().injectInto(this)
    }

    private fun setupUi() {
        showCarsWhenAvailable()
        setupRecycler()
        updateProgressBar()
    }

    private fun updateProgressBar() {
        viewModel.isUpdating.observe(this, Observer { isUpdating ->
            if (isUpdating) {
                showLoadingInProgress()
            } else {
                showRecyclerViewOnly()
            }
        })
        viewModel.isError.observe(this, Observer { isError ->
            if (isError) {
                showLoadingError()
            }
        })
    }

    private fun showCarsWhenAvailable() {
        viewModel.carsList.observe(this, Observer {
            showCars(it)
        })
    }

    private fun showCars(cars: List<UiCar>) {
        carRecyclerViewAdapter.setData(cars)
    }

    private fun showRecyclerViewOnly() {
        progress_bar.visibility = View.GONE
        recycler_view.visibility = View.VISIBLE
    }

    private fun setupRecycler() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = carRecyclerViewAdapter
    }

    private fun showLoadingError() {
        Snackbar.make(parent_layout, getString(R.string.loading_error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) {
                viewModel.retryFetching()
            }
            .show()
        showRecyclerViewOnly()
    }

    private fun showLoadingInProgress() {
        progress_bar.visibility = View.VISIBLE
        recycler_view.visibility = View.GONE
    }
}