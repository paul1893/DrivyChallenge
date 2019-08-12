package com.tech.core.list.repository

import com.tech.core.entities.Car

interface CarListRepository {
    fun getCarList(fromCache: Boolean = false) : List<Car>
}