package com.egecius.architecturesdemo.cleanarch.a_frameworks.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.egecius.architecturesdemo.R

class CleanArchMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
