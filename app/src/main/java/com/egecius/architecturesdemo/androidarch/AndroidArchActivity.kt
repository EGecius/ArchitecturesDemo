package com.egecius.architecturesdemo.androidarch

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.egecius.architecturesdemo.R
import com.egecius.architecturesdemo.androidarch.di.AndroidArchActivityModule
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarClick
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarDetailActivity
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarRecyclerViewAdapter
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.OnCarClickListener
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCar
import com.egecius.architecturesdemo.cleanarch.shared.MyApplication
import com.egecius.architecturesdemo.databinding.ActivityCleanArchBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class AndroidArchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: AndroidArchViewModel

    private lateinit var binding: ActivityCleanArchBinding

    private val carRecyclerViewAdapter = CarRecyclerViewAdapter(object : OnCarClickListener {
        override fun onClick(carClick: CarClick) {
            CarDetailActivity.start(this@AndroidArchActivity, carClick)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCleanArchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        injectDependencies()
        setupUi()
    }

    private fun injectDependencies() {
        (application as MyApplication).appComponent.plus(AndroidArchActivityModule(this))
            .injectInto(this)
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
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }

    private fun setupRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = carRecyclerViewAdapter
    }

    private fun showLoadingError() {
        Snackbar.make(binding.root, getString(R.string.loading_error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) {
                viewModel.retryFetching()
            }
            .show()
        showRecyclerViewOnly()
    }

    private fun showLoadingInProgress() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }
}