package com.tech.repository.cars

import com.nhaarman.mockitokotlin2.given
import com.tech.core.entities.Car
import com.tech.core.entities.Owner
import com.tech.core.entities.Rating
import org.assertj.core.api.Assertions
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CarListRepositoryImplTest {

    @Mock
    private lateinit var dataSource: CarListDataSource
    @InjectMocks
    lateinit var repository: CarListRepositoryImpl

    @Test
    fun getCarList() {
        // GIVEN
        val carListJSON = listOf(
            CarJSON(
                model = "C3",
                brand = "Citroen",
                picture_url = "https://image/c3.jpg",
                price_per_day = 15,
                rating = RatingJSON(
                    average = 3.5f,
                    count = 200
                ),
                owner = OwnerJSON(
                    name = "Jean Paul",
                    picture_url = "https://image/jp.jpg",
                    rating = RatingJSON(
                        average = 2.0f,
                        count = 255
                    )
                )
            )
        )
        given(dataSource.getCarList()).willReturn(carListJSON)

        // WHEN
        val result = repository.getCarList()

        // THEN
        Assertions.assertThat(result).isEqualTo(
            listOf(
                Car(
                    id = "https://image/c3.jpghttps://image/jp.jpgJean PaulC3Citroen",
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
        )

    }
}