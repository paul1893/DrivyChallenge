package com.tech.drivychallenge.list.injection

import com.tech.drivychallenge.common.FeatureScope
import com.tech.drivychallenge.common.module.RepositoryModule
import com.tech.drivychallenge.list.view.CarListActivity
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [CarListModule::class, RepositoryModule::class])
interface CarListComponent {
    fun inject(activity: CarListActivity)
}