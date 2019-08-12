package com.tech.core.list

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.then
import com.tech.core.entities.Car
import com.tech.core.entities.Owner
import com.tech.core.entities.Rating
import com.tech.core.list.presenter.CarListPresenter
import com.tech.core.list.repository.CarListRepository
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CarListInteractorTest {

    @Mock
    private lateinit var repository: CarListRepository
    @Mock
    private lateinit var presenter: CarListPresenter
    @InjectMocks
    private lateinit var interactor: CarListInteractor

    @Test
    fun loadCars() {
        // GIVEN
        val carList = listOf(
            Car(
                id = "carId",
                model = "C3",
                brand = "Citroen",
                pictureURL = "https://image/c3.jpg",
                pricePerDay = 15,
                rating = Rating(
                    average = 3.5f,
                    count = 200
                ),
                owner = Owner(
                    name = "Jean Paul",
                    pictureURL = "https://image/jp.jpg",
                    rating = Rating(
                        average = 2.0f,
                        count = 255
                    )
                )
            )
        )
        given(repository.getCarList()).willReturn(carList)

        // WHEN
        interactor.loadCars()

        // THEN
        then(presenter).should(only()).presentCarList(carList)
    }
}