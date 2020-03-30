package com.egecius.architecturesdemo.cleanarch.a_frameworks.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.egecius.architecturesdemo.R
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.CleanArcActivityPresenter
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCar
import com.egecius.architecturesdemo.cleanarch.di.CleanArcActivityModule
import com.egecius.architecturesdemo.cleanarch.shared.MyApplication
import com.egecius.architecturesdemo.cleanarch.shared.setGone
import com.egecius.architecturesdemo.cleanarch.shared.setVisible
import com.egecius.architecturesdemo.databinding.ActivityAndroidArchBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class CleanArchActivity : AppCompatActivity(), CleanArcActivityPresenter.View {

    private lateinit var binding: ActivityAndroidArchBinding

    @Inject
    lateinit var presenter: CleanArcActivityPresenter

    private val carRecyclerViewAdapter = CarRecyclerViewAdapter(object : OnCarClickListener {
        override fun onClick(carClick: CarClick) {
            presenter.onClick(carClick)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAndroidArchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()
        injectDependencies()
    }

    private fun setupRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = carRecyclerViewAdapter
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
        (application as MyApplication).appComponent.plus(CleanArcActivityModule(this))
            .injectInto(this)
    }

    override fun showCars(uiCarsList: List<UiCar>) {
        showCarListOnly()
        carRecyclerViewAdapter.setData(uiCarsList)
    }

    private fun showCarListOnly() {
        binding.recyclerView.setVisible()
        binding.progressBar.setGone()
    }

    override fun showErrorMsg() {
        Snackbar.make(binding.root, getString(R.string.loading_error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) {
                presenter.retryShowingCars()
            }
            .show()
    }
}
