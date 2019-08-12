package com.tech.drivychallenge.detail.controller

import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.then
import com.tech.core.detail.CarDetailInteractor
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CarDetailControllerImplTest {

    @Mock
    private lateinit var interactor: CarDetailInteractor
    @InjectMocks
    private lateinit var controller: CarDetailControllerImpl

    @Test
    fun loadCar() {
        // WHEN
        controller.loadCar("carId")

        // THEN
        then(interactor).should(only()).loadCar("carId")
    }
}