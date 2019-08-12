package com.tech.drivychallenge.common.module

import com.tech.core.list.repository.CarListRepository
import com.tech.repository.cars.CarListDataSource
import com.tech.repository.cars.CarListRepositoryImpl
import com.tech.repository.cars.CarListService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class RepositoryModule {

    @Provides
    fun provideCarListRepository(
        dataSource: CarListDataSource
    ): CarListRepository =
        CarListRepositoryImpl(dataSource)

    @Provides
    fun provideCarListService(
        @Named(NetworkModule.CONVERTER_MOSHI) retrofit: Retrofit
    ): CarListService = retrofit.create(CarListService::class.java)

    @Provides
    fun provideCarListDataSource(service: CarListService) =
        CarListDataSource(service)
}