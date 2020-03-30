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

    @Before
    fun setUp() {
        sut = CarsRepoImpl(networkService, carDao, jsonCarMapper)
        given(jsonCarMapper.toCars(jsonCarsList)).willReturn(carsList)
    }

    @Test
    fun `returns list of cars when network returns`() = runBlockingTest {
        givenNetworkWillReturn()

        val result = sut.getCars()

        assertThat(result).isEqualTo(carsList)
    }

    private suspend fun givenNetworkWillReturn() {
        given(networkService.getCarsFull()).willReturn(jsonCarsList)
    }

}