package com.tech.core.detail

import com.tech.core.detail.presenter.CarDetailPresenter
import com.tech.core.list.repository.CarListRepository

class CarDetailInteractor(
    private val repository: CarListRepository,
    private val presenter: CarDetailPresenter
) {
    fun loadCar(carId: String) {
        val carList = repository.getCarList()
        carList.find { it.id == carId }
            ?.let { presenter.presentCar(it) }
            ?: presenter.presentError()
    }
}