package com.egecius.architecturesdemo.cleanarch.b_adapters.network

import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import com.nhaarman.mockitokotlin2.given
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

    private val jsonCarsList = listOf(JsonCar("Tesla 3", "img"))
    private val carsList = listOf(Car("Tesla 3", "img"))

    private val cachedCarsList = listOf(Car("Wv e-Golf", "img 2"))


    @Before
    fun setUp() {
        sut = CarsRepoImpl(networkService, carDao, jsonCarMapper)
        given(jsonCarMapper.toCars(jsonCarsList)).willReturn(carsList)
    }

    @Test
    fun `returns list of cars when network returns`() = runBlockingTest {
        givenNetworkWillReturnSuccessfully()

        val result = sut.getCars()

        assertThat(result).isEqualTo(carsList)
    }

    private suspend fun givenNetworkWillReturnSuccessfully() {
        given(networkService.getCarsFull()).willReturn(jsonCarsList)
    }

    @Test
    fun `returns cashed data when network returns empty result`() = runBlockingTest {
        givenNetworkWillReturnEmpty()
        givenCacheDataWillReturnSuccessfully()

        val result = sut.getCars()

        assertThat(result).isEqualTo(cachedCarsList)
    }

    private suspend fun givenCacheDataWillReturnSuccessfully() {
        given(carDao.loadAllCars()).willReturn(cachedCarsList)
    }

    private suspend fun givenNetworkWillReturnEmpty() {
        given(networkService.getCarsFull()).willReturn(emptyList())
    }
}