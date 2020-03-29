package com.egecius.architecturesdemo.cleanarch.a_frameworks.retrofit

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockWebServer

class MockWebSeverInitializer {

    private lateinit var mockWebServer: MockWebServer

    fun init() {
        setupMockWebSever()
    }

    @SuppressLint("CheckResult") // no need to keep Disposable here
    private fun setupMockWebSever() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MockWebServerDispatcher()

        Completable.fromCallable { mockWebServer.start(PORT) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.v(TAG, "setupMockWebSever() success")
            }, {
                Log.e(TAG, "setupMockWebSever() exception: $it")
            })
    }

    companion object {
        const val TAG = "MockWebSeverInitializer"
        const val PORT = 54034
        const val BASE_URL: String = "http://localhost:$PORT"
    }
}
