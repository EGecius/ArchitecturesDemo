package com.egecius.architecturesdemo.cleanarch.b_adapters

import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UiCarsMapperTest {

    private lateinit var sut: UiCarsMapper

    @Before
    fun setUp() {
        sut = UiCarsMapper()
    }

    @Test
    fun `maps to UI cars`() {
        val carsList = listOf(
            Car("Tesla Y", "img 1"),
            Car("VW e-golf", "img 2")
        )

        val resultList = sut.toUiCars(carsList)

        assertThat(resultList.size).isEqualTo(2)
        assertThat(resultList[0]).isEqualTo(UiCar("Tesla Y", "img 1"))
        assertThat(resultList[1]).isEqualTo(UiCar("VW e-golf", "img 2"))
    }
}