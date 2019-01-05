package com.padmitriy.android.rndrx

import android.app.Application
import com.padmitriy.android.rndrx.di.AppComponent

class RndRxApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = AppComponent.Initializer.init(this)
        appComponent.inject(this)
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}