package com.egecius.architecturesdemo.cleanarch.b_adapters

import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import com.egecius.architecturesdemo.cleanarch.b_adapters.CleanArcActivityPresenter.*
import com.egecius.architecturesdemo.cleanarch.c_usecases.GetCarsInteractor
import com.egecius.architecturesdemo.cleanarch.d_domain.Car
import com.egecius.architecturesdemo.utils.TestInteractorSchedulers
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CleanArcActivityPresenterTest {

    @Mock
    private lateinit var getCarsInteractor: GetCarsInteractor
    @Mock
    private lateinit var navigator: Navigator
    @Mock
    private lateinit var view: View

    private val carsList = listOf(Car("Tesla 3" ,"img"))
    private val uiCarsList = listOf(Car("Tesla 3" ,"img"))

    /** sut - System Under Test */
    private lateinit var sut: CleanArcActivityPresenter

    @Before
    fun setUp() {
        sut = CleanArcActivityPresenter(navigator, getCarsInteractor, TestInteractorSchedulers())
    }

    @Test
    fun `shows list of cars`() {
        givenListOfCarsWillBeReturned()
    }


    private fun givenListOfCarsWillBeReturned() {
        given(getCarsInteractor.getCarsSingle()).willReturn(Single.just(carsList))

        sut.onStart(view)

        verify(view).showCars(uiCarsList)
    }
}