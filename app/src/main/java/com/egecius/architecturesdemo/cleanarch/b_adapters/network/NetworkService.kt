package com.egecius.architecturesdemo.cleanarch.b_adapters.network

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    // only works with Heroku base url
    @GET("electric")
    fun getCarsByPages(@Query("page") page: Int): Call<List<JsonCar>>

    @GET(ENDPOINT_CARS_FULL)
    suspend fun getCarsFull(): List<JsonCar>

    @GET(ENDPOINT_CARS_FULL)
    fun getCarsFullSingle(): Single<List<JsonCar>>

    companion object {
        const val ENDPOINT_CARS_FULL = "electric_full"
    }

}