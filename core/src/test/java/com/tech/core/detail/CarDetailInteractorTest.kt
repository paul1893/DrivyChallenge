package com.tech.core.detail

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.then
import com.tech.core.detail.presenter.CarDetailPresenter
import com.tech.core.entities.Car
import com.tech.core.entities.Owner
import com.tech.core.entities.Rating
import com.tech.core.list.repository.CarListRepository
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CarDetailInteractorTest {

    @Mock
    private lateinit var repository: CarListRepository
    @Mock
    private lateinit var presenter: CarDetailPresenter
    @InjectMocks
    private lateinit var interactor: CarDetailInteractor

    @Test
    fun loadCar() {
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
        interactor.loadCar("carId")

        // THEN
        then(presenter).should(only()).presentCar(
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
    }

    @Test
    fun `loadCar when not found the car`(){
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
        interactor.loadCar("randomId")

        // THEN
        then(presenter).should(only()).presentError()
    }
}