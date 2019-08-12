package com.tech.core.list

import com.tech.core.list.presenter.CarListPresenter
import com.tech.core.list.repository.CarListRepository

class CarListInteractor(
    private val repository: CarListRepository,
    private val presenter: CarListPresenter
) {
    fun loadCars() {
        val carList = repository.getCarList()
        presenter.presentCarList(carList)
    }
}