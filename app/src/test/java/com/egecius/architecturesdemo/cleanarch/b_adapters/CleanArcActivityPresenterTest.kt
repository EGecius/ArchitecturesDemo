package com.egecius.architecturesdemo.cleanarch.b_adapters

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CleanArcActivityPresenterTest {

    /** sut - System Under Test */
    private lateinit var sut: CleanArcActivityPresenter

    @Before
    fun setUp() {
        sut = CleanArcActivityPresenter(navigator, getCarsInteractor, TestInteractorSchedulers())
    }

    @Test
    fun `shows list of cars`() {

    }


}