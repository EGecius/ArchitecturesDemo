package com.egecius.architecturesdemo.androidarch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.egecius.architecturesdemo.androidarch.di.AndroidArchActivityModule
import com.egecius.architecturesdemo.cleanarch.di.CleanArcActivityModule
import com.egecius.architecturesdemo.cleanarch.shared.MyApplication
import com.egecius.architecturesdemo.databinding.ActivityAndroidArchBinding
import javax.inject.Inject

class AndroidArchActivity : AppCompatActivity() {

    @Inject
    lateinit var androidViewModel: AndroidArchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAndroidArchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        injectDependencies()
    }

    private fun injectDependencies() {
        (application as MyApplication).appComponent.plus(AndroidArchActivityModule(this))
            .injectInto(this)
    }
}