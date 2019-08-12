package com.tech.drivychallenge

import android.app.Application
import android.content.Context
import com.tech.drivychallenge.common.DaggerMainComponent
import com.tech.drivychallenge.common.MainComponent
import com.tech.drivychallenge.common.module.MainModule

class DrivyChallengeApplication : Application() {
    companion object {
        fun getComponent(context: Context): MainComponent =
            (context.applicationContext as DrivyChallengeApplication).component
    }

    private lateinit var component: MainComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerMainComponent.builder().mainModule(MainModule(this)).build()
        component.inject(this)
    }
}