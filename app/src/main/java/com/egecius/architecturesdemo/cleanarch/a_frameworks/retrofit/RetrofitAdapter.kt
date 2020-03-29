package com.egecius.architecturesdemo.cleanarch.a_frameworks.retrofit

import com.egecius.architecturesdemo.cleanarch.b_adapters.network.NetworkService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitAdapter {

    private val mockWebServerBaseUrl = MockWebSeverInitializer.BASE_URL
    private val baseUrlHeroku = "https://mighty-spire-24044.herokuapp.com/"

    fun setupRetrofit(): NetworkService {
        return Retrofit.Builder()
            .baseUrl(mockWebServerBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createLoggingOkHttpClient())
            .build()
            .create(NetworkService::class.java)
    }

    private fun createLoggingOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}

