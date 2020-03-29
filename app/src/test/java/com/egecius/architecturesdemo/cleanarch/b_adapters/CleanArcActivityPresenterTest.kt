package com.egecius.architecturesdemo.cleanarch.b_adapters

import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.CarClick
import com.egecius.architecturesdemo.cleanarch.a_frameworks.android.Navigator
import com.egecius.architecturesdemo.cleanarch.b_adapters.CleanArcActivityPresenter.*
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCar
import com.egecius.architecturesdemo.cleanarch.b_adapters.ui.UiCarsMapper
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
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class CleanArcActivityPresenterTest {

    /** sut - System Under Test */
    private lateinit var sut: CleanArcActivityPresenter

    @Mock
    private lateinit var uiMapper: UiCarsMapper
    @Mock
    private lateinit var getCarsInteractor: GetCarsInteractor
    @Mock
    private lateinit var navigator: Navigator
    @Mock
    private lateinit var view: View
    @Mock
    private lateinit var carClick: CarClick

    private val carsList = listOf(Car("Tesla 3" ,"img"))
    private val uiCarsList = listOf(UiCar("Tesla 3", "img"))


    @Before
    fun setUp() {
        sut = CleanArcActivityPresenter(navigator, getCarsInteractor, uiMapper, TestInteractorSchedulers())
        given(uiMapper.toUiCars(carsList)).willReturn(uiCarsList)
    }

    @Test
    fun `shows list of cars on start`() {
        givenListOfCarsWillBeReturned()

        sut.onStart(view)

        verify(view).showCars(uiCarsList)
    }

    private fun givenListOfCarsWillBeReturned() {
        given(getCarsInteractor.getCarsSingle()).willReturn(Single.just(carsList))
    }

    @Test
    fun `shows error message when fetching cars fails`() {
        givenListOfCarsWillFailToReturn()

        sut.onStart(view)

        verify(view).showErrorMsg()
    }

    private fun givenListOfCarsWillFailToReturn() {
        given(getCarsInteractor.getCarsSingle()).willReturn(Single.error(Exception()))
    }

    @Test
    fun `opens card detail screen on user click`() {
        sut.onClick(carClick)

        verify(navigator).openDetailScreen(carClick)
    }

    @Test
    fun `shows list of cars when 'retry' clicked`() {
        givenListOfCarsWillFailToReturn()
        sut.onStart(view)

        givenListOfCarsWillBeReturned()
        sut.retryShowingCars()

        verify(view).showCars(uiCarsList)
    }
}