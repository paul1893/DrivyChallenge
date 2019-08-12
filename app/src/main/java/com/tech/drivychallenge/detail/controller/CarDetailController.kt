package com.tech.drivychallenge.detail.controller

import com.tech.core.detail.CarDetailInteractor
import java.util.concurrent.Executor

interface CarDetailController {
    fun loadCar(carId: String)
}

class CarDetailControllerImpl(
    private val interactor: CarDetailInteractor
) : CarDetailController {

    override fun loadCar(carId: String) = interactor.loadCar(carId)
}

class CarDetailControllerDecorator(
    private val impl: CarDetailControllerImpl,
    private val executor: Executor
) : CarDetailController {
    override fun loadCar(cardId: String) {
        executor.execute {
            impl.loadCar(cardId)
        }
    }
}
