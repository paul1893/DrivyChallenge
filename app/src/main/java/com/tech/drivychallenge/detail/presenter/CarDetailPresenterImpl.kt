package com.tech.drivychallenge.detail.presenter

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import com.tech.core.detail.presenter.CarDetailPresenter
import com.tech.core.entities.Car
import com.tech.drivychallenge.R
import com.tech.drivychallenge.detail.model.CarDetailViewModel

class CarDetailPresenterImpl(
    private val observable: CarDetailObservable,
    private val resources: Resources
) : CarDetailPresenter {

    override fun presentCar(car: Car) {
        observable.car.postValue(
            CarDetailViewModel(
                id = car.pictureURL, // TODO PBA
                imageURL = car.pictureURL,
                name = car.model,
                price = resources.getString(R.string.price_per_day, car.pricePerDay),
                rating = car.rating.average,
                ratingLabel = resources.getString(R.string.rating, car.rating.average),
                personName = car.owner.name,
                personImageUrl = car.owner.pictureURL,
                personRating = car.owner.rating.average
            )
        )
    }

    override fun presentError() {
        observable.error.postValue(Unit)
    }
}

data class CarDetailObservable(
    val car: MutableLiveData<CarDetailViewModel> = MutableLiveData(),
    val error: MutableLiveData<Unit> = MutableLiveData()
)