package com.egecius.architecturesdemo.androidarch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCar
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCarsMapper
import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AndroidArchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: AndroidArchViewModel

    @Mock
    private lateinit var carsRepository: CarsRepo

    @Mock
    private lateinit var navigator: Navigator

    @Mock
    private lateinit var uiCarsMapper: UiCarsMapper

    private val carsList = listOf(Car("name_1", "img_2"))
    private val uiCarsList = listOf(UiCar("name_1", "img_2"))


    @Before
    fun setUp() {
        sut = AndroidArchViewModel(carsRepository, uiCarsMapper, navigator)
    }

    @Test
    fun `demo for testing live data`() {
        val result = sut.liveDataDemo.value

        assertThat(result).isEqualTo(1)
    }

    // TODO: 18/04/2020 make the test pass
    @Test
    fun `shows cars List`() = runBlockingTest {
        givenCarsWillBeEmitted()

        val result = sut.carsList.value

        assertThat(result).isEqualTo(uiCarsList)
    }

    private suspend fun givenCarsWillBeEmitted() {
        given(carsRepository.getCars()).willReturn(carsList)
    }
}