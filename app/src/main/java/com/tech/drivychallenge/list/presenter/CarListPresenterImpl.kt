package com.tech.drivychallenge.list.presenter

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import com.tech.core.entities.Car
import com.tech.core.list.presenter.CarListPresenter
import com.tech.drivychallenge.R
import com.tech.drivychallenge.list.model.CarViewModel

class CarListPresenterImpl(
    private val observable: CarListObservable,
    private val resources: Resources
) : CarListPresenter {

    override fun presentCarList(carList: List<Car>) {
        observable.carList.postValue(carList.map {
            CarViewModel(
                id = it.id,
                imageURL = it.pictureURL,
                name = it.model,
                price = resources.getString(R.string.price_per_day, it.pricePerDay),
                rating = it.rating.average,
                ratingLabel = resources.getString(R.string.rating, it.rating.average)
            )
        })
    }
}

data class CarListObservable(
    val carList: MutableLiveData<List<CarViewModel>> = MutableLiveData()
)