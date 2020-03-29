package com.egecius.architecturesdemo.cleanarch.a_frameworks.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.egecius.architecturesdemo.R
import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_car_detail.*

class CarDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail)

        setUi()
    }

    private fun setUi() {
        val car  = intent.extras!!.get(KEY_CAR) as Car
        title = car.name
        Picasso.get().load(car.img).into(image)
    }

    companion object {

        const val KEY_CAR = "key_car"

    	fun start(
            originActivity: Activity,
            carClick: CarClick
        ) {
            val intent = Intent(originActivity, CarDetailActivity::class.java)
            intent.putExtra(KEY_CAR, carClick.car)
            val pairImage = Pair(carClick.imageView as View, "car_image")
            val pairTitle = Pair(carClick.titleView as View, "car_title")
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(originActivity, pairImage, pairTitle)
            originActivity.startActivity(intent, options.toBundle())
        }
    }
}