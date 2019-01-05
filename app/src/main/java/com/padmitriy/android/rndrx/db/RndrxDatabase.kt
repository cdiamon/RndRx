package com.padmitriy.android.rndrx.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.padmitriy.android.rndrx.db.dao.SummitsDao
import com.padmitriy.android.rndrx.model.Summit

@Database(entities = [Summit::class], version = 1, exportSchema = false)
//@TypeConverters(DateTypeConverter::class)
abstract class RndrxDatabase : RoomDatabase() {

    abstract fun getSummitDao(): SummitsDao

}