package com.tech.drivychallenge.detail.module

import com.tech.drivychallenge.common.FeatureScope
import com.tech.drivychallenge.common.module.RepositoryModule
import com.tech.drivychallenge.detail.view.CarDetailActivity
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [CarDetailModule::class, RepositoryModule::class])
interface CarDetailComponent {
    fun inject(activity: CarDetailActivity)
}