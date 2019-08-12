package com.tech.drivychallenge.list.controller

import com.tech.core.list.CarListInteractor
import java.util.concurrent.Executor

interface CarListController {
    fun loadCars()
}

class CarListControllerImpl(
    private val interactor: CarListInteractor
) : CarListController {
    override fun loadCars() = interactor.loadCars()
}

class CarListControllerDecorator(
    private val impl: CarListControllerImpl,
    private val executor: Executor
) : CarListController {
    override fun loadCars() = executor.execute {
        impl.loadCars()
    }
}