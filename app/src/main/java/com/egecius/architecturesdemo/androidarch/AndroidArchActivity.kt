package com.egecius.architecturesdemo.androidarch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.egecius.architecturesdemo.androidarch.di.AndroidArchActivityModule
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarClick
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarRecyclerViewAdapter
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.OnCarClickListener
import com.egecius.architecturesdemo.cleanarch.shared.MyApplication
import com.egecius.architecturesdemo.databinding.ActivityAndroidArchBinding
import javax.inject.Inject

class AndroidArchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: AndroidArchViewModel

    private val adapter = CarRecyclerViewAdapter(object : OnCarClickListener {
        override fun onClick(carClick: CarClick) {
            viewModel.onCarClick(carClick)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAndroidArchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler(binding)
        injectDependencies()
        observe()
    }

    private fun setupRecycler(binding: ActivityAndroidArchBinding) {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun observe() {
        viewModel.carsList.observe(this,
            Observer { uiCarsList -> adapter.setData(uiCarsList) })
    }

    private fun injectDependencies() {
        (application as MyApplication).appComponent.plus(AndroidArchActivityModule(this))
            .injectInto(this)
    }
}