@file:Suppress("UsePropertyAccessSyntax")

package com.egecius.architecturesdemo.androidarch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCar
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCarsMapper
import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import com.egecius.architecturesdemo.cleanarch.d_domain.CarsRepo
import com.egecius.architecturesdemo.utils.MainCoroutineRule
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
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

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val carsList = listOf(Car("name_1", "img_2"))
    private val uiCarsList = listOf(UiCar("name_1", "img_2"))


    @Before
    fun setUp() {
        sut = AndroidArchViewModel(carsRepository, uiCarsMapper, navigator, testCoroutineDispatcher)
    }

    // TODO: 18/04/2020 make the test pass
    @Test
    fun `shows cars List`() = testCoroutineDispatcher.runBlockingTest {
        givenCarsWillBeEmitted()

        val result = sut.carsList.value

        assertThat(result).isEqualTo(uiCarsList)
    }

    private suspend fun givenCarsWillBeEmitted() {
        given(carsRepository.getCars()).willReturn(carsList)
    }

    @Test
    fun `shows error message`() {
        givenCarsEmissionFails()

        // TODO: 18/04/2020 finish
    }

    private fun givenCarsEmissionFails() {
        TODO("not implemented")
    }

    @Test
    fun `hides error message when 'retry' clicked`() {
        val result: Boolean? = sut.isError.value

        assertThat(result).isFalse()
    }

    @Test
    fun `viewModelScope job intercepts exception in invokeOnCompletion`() {
        var resultThrowable: Throwable? = null

        sut.viewModelScope.launch{
            throw Exception("egis")
        }.invokeOnCompletion {
            resultThrowable = it
        }

        assertThat(resultThrowable?.message).isEqualTo("egis")
    }
}