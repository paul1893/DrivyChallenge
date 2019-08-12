package com.tech.drivychallenge.detail.module

import android.content.res.Resources
import com.tech.core.detail.CarDetailInteractor
import com.tech.core.detail.presenter.CarDetailPresenter
import com.tech.core.list.repository.CarListRepository
import com.tech.drivychallenge.common.FeatureScope
import com.tech.drivychallenge.common.module.MainModule
import com.tech.drivychallenge.detail.controller.CarDetailController
import com.tech.drivychallenge.detail.controller.CarDetailControllerDecorator
import com.tech.drivychallenge.detail.controller.CarDetailControllerImpl
import com.tech.drivychallenge.detail.presenter.CarDetailObservable
import com.tech.drivychallenge.detail.presenter.CarDetailPresenterImpl
import com.tech.drivychallenge.detail.view.CarDetailActivity
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import javax.inject.Named

@Module
class CarDetailModule() {

    @Provides
    fun provideCarDetailInteractor(
        repository: CarListRepository,
        presenter: CarDetailPresenter
    ): CarDetailInteractor =
        CarDetailInteractor(repository, presenter)

    @FeatureScope
    @Provides
    fun provideCarDetailObservable() = CarDetailObservable()

    @FeatureScope
    @Provides
    fun provideCarDetailPresenter(observable: CarDetailObservable, resources: Resources): CarDetailPresenter
            = CarDetailPresenterImpl(observable, resources)

    @Provides
    fun provideCarDetailController(
        interactor: CarDetailInteractor,
        @Named(MainModule.BACKGROUND_THREAD) executor: Executor
    ): CarDetailController =
        CarDetailControllerDecorator(
            CarDetailControllerImpl(interactor), executor
        )
}