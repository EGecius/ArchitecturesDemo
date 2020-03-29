package com.egecius.architecturesdemo.cleanarch.a_frameworks.retrofit

import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RetrofitAdapter {

    private val mockWebServerBaseUrl = MockWebSeverInitializer.BASE_URL
    private val baseUrlHeroku = "https://mighty-spire-24044.herokuapp.com/"

    fun setupRetrofit(): CarRetrofitService {
        return Retrofit.Builder()
            .baseUrl(mockWebServerBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createLoggingOkHttpClient())
            .build()
            .create(CarRetrofitService::class.java)
    }

    private fun createLoggingOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}

interface CarRetrofitService {

    // only works with Heroku base url
    @GET("electric")
    fun getCarsByPages(@Query("page") page: Int): Call<List<Car>>

    @GET(ENDPOINT_CARS_FULL)
    suspend fun getCarsFull(): List<Car>

    companion object {
        const val ENDPOINT_CARS_FULL = "electric_full"
    }

}