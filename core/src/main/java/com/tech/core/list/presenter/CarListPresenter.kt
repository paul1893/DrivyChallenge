package com.tech.core.list.presenter

import com.tech.core.entities.Car

interface CarListPresenter {
    fun presentCarList(carList: List<Car>)
}