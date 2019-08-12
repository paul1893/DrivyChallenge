package com.tech.core.detail.presenter

import com.tech.core.entities.Car

interface CarDetailPresenter {
    fun presentCar(car: Car)
    fun presentError()
}