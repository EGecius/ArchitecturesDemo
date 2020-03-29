package com.egecius.architecturesdemo.cleanarch.a_frameworks.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.egecius.architecturesdemo.R
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_car_detail.*

class CarDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail)

        showCar()
    }

    private fun showCar() {
        val car = intent.extras!!.get(KEY_CAR) as UiCar
        title = car.title
        Picasso.get().load(car.imgUrl).into(image)
    }

    companion object {

        private const val KEY_CAR = "key_car"
        private const val KEY_CAR_IMAGE = "car_image"
        private const val KEY_CAR_TITLE = "car_title"

        fun start(
            originActivity: Activity,
            carClick: CarClick
        ) {
            val intent = Intent(originActivity, CarDetailActivity::class.java)
            intent.putExtra(KEY_CAR, carClick.car)
            val pairImage = Pair(carClick.imageView as View, KEY_CAR_IMAGE)
            val pairTitle = Pair(carClick.titleView as View, KEY_CAR_TITLE)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(originActivity, pairImage, pairTitle)
            originActivity.startActivity(intent, options.toBundle())
        }
    }
}