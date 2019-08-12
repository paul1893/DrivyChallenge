package com.tech.drivychallenge.detail.presenter

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.given
import com.tech.core.entities.Car
import com.tech.core.entities.Owner
import com.tech.core.entities.Rating
import com.tech.drivychallenge.R
import com.tech.drivychallenge.detail.model.CarDetailViewModel
import org.assertj.core.api.Assertions
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CarDetailPresenterImplTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val observable = CarDetailObservable()
    @Mock
    private lateinit var resources: Resources
    private lateinit var presenter: CarDetailPresenterImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = CarDetailPresenterImpl(
            observable,
            resources
        )
    }

    @Test
    fun presentCar() {
        // GIVEN
        val car = Car(
            id = "id",
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
        given(resources.getString(R.string.price_per_day, 15)).willReturn("15 €/j")
        given(resources.getString(R.string.rating, 3.5f)).willReturn("3.5")
        given(resources.getString(R.string.car_detail_name, "C3", "Citroen")).willReturn("C3 - Citroen")

        // WHEN
        presenter.presentCar(car)

        // THEN
        Assertions.assertThat(observable.car.value).isEqualTo(
            CarDetailViewModel(
                id = "id",
                imageURL = "https://image/c3.jpg",
                name = "C3 - Citroen",
                price = "15 €/j",
                rating = 3.5f,
                ratingLabel = "3.5",
                personName = "Jean Paul",
                personImageUrl = "https://image/jp.jpg",
                personRating = 2.0f
            )
        )
    }

    @Test
    fun presentError() {
        // WHEN
        presenter.presentError()

        // THEN
        Assertions.assertThat(observable.error.value).isEqualTo(Unit)
    }
}