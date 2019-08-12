package com.tech.drivychallenge.common

import com.tech.drivychallenge.DrivyChallengeApplication
import com.tech.drivychallenge.common.module.DiskModule
import com.tech.drivychallenge.common.module.MainModule
import com.tech.drivychallenge.common.module.NetworkModule
import com.tech.drivychallenge.list.injection.CarListComponent
import com.tech.drivychallenge.list.injection.CarListModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MainModule::class,
        NetworkModule::class,
        DiskModule::class
    ]
)
interface MainComponent {
    fun plus(module: CarListModule): CarListComponent
    fun inject(application: DrivyChallengeApplication)
}