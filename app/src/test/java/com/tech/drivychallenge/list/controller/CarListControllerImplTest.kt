package com.tech.drivychallenge.list.controller

import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.then
import com.tech.core.list.CarListInteractor
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CarListControllerImplTest {

    @Mock
    private lateinit var interactor: CarListInteractor
    @InjectMocks
    private lateinit var controller: CarListControllerImpl

    @Test
    fun loadCars() {
        // WHEN
        controller.loadCars()
        // THEN
        then(interactor).should(only()).loadCars()
    }
}