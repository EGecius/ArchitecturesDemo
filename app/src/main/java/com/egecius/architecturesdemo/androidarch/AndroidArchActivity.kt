package com.egecius.architecturesdemo.androidarch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.egecius.architecturesdemo.R
import com.egecius.architecturesdemo.androidarch.di.AndroidArchActivityModule
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarClick
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarRecyclerViewAdapter
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.OnCarClickListener
import com.egecius.architecturesdemo.cleanarch.shared.MyApplication
import com.egecius.architecturesdemo.databinding.ActivityAndroidArchBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class AndroidArchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: AndroidArchViewModel

    lateinit var binding: ActivityAndroidArchBinding

    private val adapter = CarRecyclerViewAdapter(object : OnCarClickListener {
        override fun onClick(carClick: CarClick) {
            viewModel.onCarClick(carClick)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAndroidArchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()
        injectDependencies()
        updateViewOnViewModelChanges()
    }

    private fun setupRecycler() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun updateViewOnViewModelChanges() {
        viewModel.carsList.observe(this,
            Observer { uiCarsList -> adapter.setData(uiCarsList) })
        viewModel.isError.observe(this, Observer { isError ->
            if (isError) {
                showErrorSnackbar()
            }
        })
    }

    private fun showErrorSnackbar() {
        Snackbar.make(binding.root, R.string.loading_error, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.retry) {
                viewModel.onClickedRetry()
            }
            .show()
    }

    private fun injectDependencies() {
        (application as MyApplication).appComponent.plus(AndroidArchActivityModule(this))
            .injectInto(this)
    }
}