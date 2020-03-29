package com.egecius.architecturesdemo.androidarch

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.egecius.architecturesdemo.R

class AndroidArchActivity : AppCompatActivity() {

    private val model: AndroidArchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_arch)
    }
}