package com.egecius.architecturesdemo.cleanarch.b_adapters.network

import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CarsRepoImplTest {

    private lateinit var sut: CarsRepoImpl

    @Mock
    private lateinit var jsonCarMapper: JsonCarMapper

    @Mock
    private lateinit var carDao: CarDao

    @Mock
    private lateinit var networkService: NetworkService

    private val jsonCarsListFromNetwork = listOf(JsonCar("Tesla 3", "img"))
    private val car0 = Car("Tesla 3", "img")
    private val carsListFromNetwork = listOf(car0)

    private val carsListFromCache = listOf(Car("Wv e-Golf", "img 2"))


    @Before
    fun setUp() {
        sut = CarsRepoImpl(networkService, carDao, jsonCarMapper)
        given(jsonCarMapper.toCars(jsonCarsListFromNetwork)).willReturn(carsListFromNetwork)
    }

    @Test
    fun `returns list of cars when network returns`() = runBlockingTest {
        givenNetworkWillReturnSuccessfully()

        val result = sut.getCars()

        assertThat(result).isEqualTo(carsListFromNetwork)
    }

    private suspend fun givenNetworkWillReturnSuccessfully() {
        given(networkService.getCarsFull()).willReturn(jsonCarsListFromNetwork)
    }

    @Test
    fun `returns cashed data when network returns empty result`() = runBlockingTest {
        givenNetworkWillReturnEmpty()
        givenCacheDataWillReturnSuccessfully()

        val result = sut.getCars()

        assertThat(result).isEqualTo(carsListFromCache)
    }

    private suspend fun givenCacheDataWillReturnSuccessfully() {
        given(carDao.loadAllCars()).willReturn(carsListFromCache)
    }

    private suspend fun givenNetworkWillReturnEmpty() {
        given(networkService.getCarsFull()).willReturn(emptyList())
    }

    @Test
    fun `updates cache when network succeeds`() = runBlockingTest {
        givenNetworkWillReturnSuccessfully()

        sut.getCars()

        verify(carDao).insertCar(car0)
    }
}