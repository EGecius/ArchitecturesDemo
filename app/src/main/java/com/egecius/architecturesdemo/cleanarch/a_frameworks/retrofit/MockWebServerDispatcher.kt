package com.egecius.architecturesdemo.cleanarch.a_frameworks.retrofit

import android.util.Log
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.util.*

class MockWebServerDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest?): MockResponse {
    	Log.v("Eg:MockWebServerDispatcher:12", "dispatch()")

        Thread.sleep(NETWORK_DELAY_MILLIS)

        if (request?.path.equals("/" + NetworkService.ENDPOINT_CARS_FULL)) {
            Log.v("Eg:MockWebServerDispatcher:15", "dispatch() ")
            return MockResponse().setBody(getElectricCars())
        }
        Log.v("Eg:MockWebServerDispatcher:19", "dispatch() 404")
        return MockResponse().setResponseCode(404)
    }

    private fun getElectricCars(): String {
        val inputStream = javaClass.getResourceAsStream("/" + "electric_cars.json")!!
        return Scanner(inputStream).useDelimiter("\\A").next()
    }

    companion object {

        private const val NETWORK_DELAY_MILLIS = 300L

    }
}
