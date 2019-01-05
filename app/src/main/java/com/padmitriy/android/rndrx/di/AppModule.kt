package com.padmitriy.android.rndrx.di

import android.content.Context
import com.padmitriy.android.rndrx.RndRxApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: RndRxApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return app.applicationContext
    }

}
