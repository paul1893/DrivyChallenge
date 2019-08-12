package com.tech.drivychallenge.list.injection

import android.content.res.Resources
import com.tech.core.list.CarListInteractor
import com.tech.core.list.presenter.CarListPresenter
import com.tech.core.list.repository.CarListRepository
import com.tech.drivychallenge.common.FeatureScope
import com.tech.drivychallenge.common.module.MainModule.Companion.BACKGROUND_THREAD
import com.tech.drivychallenge.list.controller.CarListController
import com.tech.drivychallenge.list.controller.CarListControllerDecorator
import com.tech.drivychallenge.list.controller.CarListControllerImpl
import com.tech.drivychallenge.list.presenter.CarListObservable
import com.tech.drivychallenge.list.presenter.CarListPresenterImpl
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import javax.inject.Named

@Module
class CarListModule {
    @Provides
    fun provideCarListInteractor(
        repository: CarListRepository,
        presenter: CarListPresenter
    ): CarListInteractor =
        CarListInteractor(repository, presenter)

    @FeatureScope
    @Provides
    fun provideCarListObservable() = CarListObservable()

    @FeatureScope
    @Provides
    fun provideCarListPresenter(observable: CarListObservable, resources: Resources): CarListPresenter
            = CarListPresenterImpl(observable, resources)

    @Provides
    fun provideCarListController(
        interactor: CarListInteractor,
        @Named(BACKGROUND_THREAD) executor: Executor
    ): CarListController =
        CarListControllerDecorator(
            CarListControllerImpl(interactor), executor
        )
}