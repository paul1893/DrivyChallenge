package com.tech.drivychallenge.common.module

import android.content.Context
import com.tech.core.list.repository.CarListRepository
import com.tech.repository.cars.CacheManager
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
        dataSource: CarListDataSource,
        cacheManager: CacheManager
    ): CarListRepository =
        CarListRepositoryImpl(dataSource, cacheManager)

    @Provides
    fun provideCarListService(
        @Named(NetworkModule.CONVERTER_MOSHI) retrofit: Retrofit
    ): CarListService = retrofit.create(CarListService::class.java)

    @Provides
    fun provideCarListDataSource(service: CarListService) =
        CarListDataSource(service)

    @Provides
    fun provideCacheManager(context: Context) =
        CacheManager(context)
}