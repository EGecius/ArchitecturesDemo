package com.egecius.architecturesdemo.cleanarch.a_frameworks.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.egecius.architecturesdemo.R
import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import com.squareup.picasso.Picasso

class CarRecyclerViewAdapter(
    private val onCarClickListener: OnCarClickListener
) : Adapter<CarRecyclerViewAdapter.MyViewHolder>() {

    private var carList: List<Car> = emptyList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.car_list_item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, i: Int) {

        holder.cardView.setOnClickListener {
            onCarClickListener.onClick(
                CarClick(carList[i], holder.image, holder.title)
            )
        }

        holder.title.text = carList[i].name
        val imgUrl = carList[i].img
        Picasso.get().load(imgUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    fun setData(cars: List<Car>) {
        carList = cars
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: View = itemView.findViewById(R.id.card_view)
        var title: TextView = itemView.findViewById(R.id.title)
        var image: ImageView = itemView.findViewById(R.id.image)
    }
}

interface OnCarClickListener {
    fun onClick(carClick: CarClick)
}

class CarClick(val car: Car, val imageView: ImageView, val titleView: TextView)