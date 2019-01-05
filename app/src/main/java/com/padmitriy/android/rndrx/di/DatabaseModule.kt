package com.padmitriy.android.rndrx.di

import android.arch.persistence.room.Room
import android.content.Context
import com.padmitriy.android.rndrx.db.RndrxDatabase
import com.padmitriy.android.rndrx.db.dao.SummitsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideRndrxDatabase(context: Context): RndrxDatabase {
        return Room
            .databaseBuilder(context, RndrxDatabase::class.java, "rndrx")
            .build()
    }

    @Provides
    @Singleton
    internal fun provideSummitsDao(rndrxDatabase: RndrxDatabase): SummitsDao {
        return rndrxDatabase.getSummitDao()
    }

}