@file:Suppress("UsePropertyAccessSyntax")

package com.egecius.architecturesdemo.androidarch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCar
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCarsMapper
import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import com.egecius.architecturesdemo.utils.MainCoroutineRule
import com.nhaarman.mockitokotlin2.doThrow
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

    private lateinit var sut: AndroidArchViewModel

    @Mock
    private lateinit var carsRepository: CarsRepo

    @Mock
    private lateinit var navigator: Navigator

    @Mock
    private lateinit var uiCarsMapper: UiCarsMapper

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    /** Sets the main coroutines dispatcher to a TestCoroutineScope for unit testing */
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val carsList = listOf(Car("name_1", "img_2"))
    private val uiCarsList = listOf(UiCar("name_1", "img_2"))

    @Before
    fun setUp() {
        given(uiCarsMapper.toUiCars(carsList)).willReturn(uiCarsList)
        sut = AndroidArchViewModel(carsRepository, uiCarsMapper, navigator)
    }

    @Test
    fun `shows cars List`() = runBlockingTest {
        givenCarsWillBeEmitted()

        sut.onCreate()

        val result = sut.carsList.value
        assertThat(result).isEqualTo(uiCarsList)
    }

    private suspend fun givenCarsWillBeEmitted() {
        given(carsRepository.getCars()).willReturn(carsList)
    }

    @Test
    fun `shows error message`() = runBlockingTest {
        givenCarsEmissionFails()

        sut.onCreate()

        val result: Boolean? = sut.isError.value
        assertThat(result).isTrue()
    }

    private suspend fun givenCarsEmissionFails() {
        doThrow(RuntimeException::class).`when`(carsRepository).getCars()
    }

    @Test
    fun `hides error message when 'retry' clicked`() {
        val result: Boolean? = sut.isError.value

        assertThat(result).isFalse()
    }

    @Test
    fun `hides progress indicator when fetching finishes`() = runBlockingTest {
        givenCarsWillBeEmitted()

        sut.onCreate()

        val result: Boolean? = sut.isFetching.value
        assertThat(result).isFalse()
    }
}