package com.egecius.architecturesdemo.cleanarch.b_adapters.network

import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class JsonCarMapperTest {

    private lateinit var sut: JsonCarMapper

    @Before
    fun setUp() {
        sut = JsonCarMapper()
    }

    @Test
    fun `maps to UI cars`() {
        val carsList = listOf(
            JsonCar("Tesla Y", "img 1"),
            JsonCar("VW e-golf", "img 2")
        )

        val resultList = sut.toCars(carsList)

        assertThat(resultList.size).isEqualTo(2)
        assertThat(resultList[0]).isEqualTo(Car("Tesla Y", "img 1"))
        assertThat(resultList[1]).isEqualTo(Car("VW e-golf", "img 2"))
    }

}