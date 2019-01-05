package com.padmitriy.android.rndrx.di

import com.padmitriy.android.rndrx.RndRxApplication
import com.padmitriy.android.rndrx.view.RatingListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(rndRxApplication: RndRxApplication)

    fun inject(ratingListActivity: RatingListActivity)

    object Initializer {
        fun init(application: RndRxApplication): AppComponent {
            return DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .build()
        }
    }
}