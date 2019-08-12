package com.tech.drivychallenge.common.module

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Named
import javax.inject.Singleton

@Module
class MainModule(private val context: Context) {
    companion object {
        const val BACKGROUND_THREAD = "backgroundThread"
        const val THREAD_COUNT = 4
    }

    @Provides
    @Singleton
    @Named(BACKGROUND_THREAD)
    fun provideBackgroundExecutor(): Executor = Executors.newFixedThreadPool(THREAD_COUNT)

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideResources(): Resources = context.resources

}
