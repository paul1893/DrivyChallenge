package com.tech.repository.cars

import com.tech.core.entities.Car
import com.tech.core.entities.Owner
import com.tech.core.entities.Rating
import com.tech.core.list.repository.CarListRepository

class CarListRepositoryImpl(
    private val dataSource: CarListDataSource
) : CarListRepository {
    override fun getCarList(): List<Car> = dataSource.getCarList().map(::transform)

    private fun transform(json: CarJSON) = Car(
        model = json.model,
        brand = json.brand,
        pictureURL = json.picture_url,
        pricePerDay = json.price_per_day,
        rating = Rating(
            average = json.rating.average,
            count = json.rating.count
        ),
        owner = Owner(
            name = json.owner.name,
            pictureURL = json.owner.picture_url,
            rating = Rating(
                average = json.owner.rating.average,
                count = json.owner.rating.count
            )
        )
    )
}