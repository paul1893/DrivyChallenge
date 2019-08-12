package com.tech.drivychallenge.list.presenter

import androidx.lifecycle.MutableLiveData
import com.tech.core.entities.Car
import com.tech.core.list.presenter.CarListPresenter
import com.tech.drivychallenge.list.model.CarViewModel

class CarListPresenterImpl(
    private val observable: CarListObservable
) : CarListPresenter {

    override fun presentCarList(carList: List<Car>) {
        // TODO PBA
    }
}

data class CarListObservable(
    val carList: MutableLiveData<List<CarViewModel>> = MutableLiveData()
)