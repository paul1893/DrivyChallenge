package com.tech.drivychallenge.list.presenter

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.given
import com.tech.core.entities.Car
import com.tech.core.entities.Owner
import com.tech.core.entities.Rating
import com.tech.drivychallenge.R
import com.tech.drivychallenge.list.model.CarViewModel
import org.assertj.core.api.Assertions
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CarListPresenterImplTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var resources: Resources
    private val observable = CarListObservable()
    private lateinit var presenter: CarListPresenterImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = CarListPresenterImpl(
            observable,
            resources
        )
    }

    @Test
    fun presentCarList() {
        // GIVEN
        val carList = listOf(
            Car(
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
        )
        given(resources.getString(R.string.price_per_day, 15)).willReturn("15 €/j")
        given(resources.getString(R.string.rating, 3.5f)).willReturn("3.5")

        // WHEN
        presenter.presentCarList(carList)

        // THEN
        Assertions.assertThat(observable.carList.value).isEqualTo(
            listOf(
                CarViewModel(
                    id = "id",
                    imageURL = "https://image/c3.jpg",
                    name = "C3",
                    price = "15 €/j",
                    rating = 3.5f,
                    ratingLabel = "3.5"
                )
            )
        )
    }
}