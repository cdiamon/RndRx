package com.padmitriy.android.rndrx.db.dao

import android.arch.persistence.room.*
import com.padmitriy.android.rndrx.model.Summit
import io.reactivex.Flowable


@Dao
interface SummitsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSummit(summit: Summit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSummitsList(summit: List<Summit>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSummit(vararg summit: Summit)

    @Delete
    fun deleteSummit(vararg summit: Summit)

    @Query("SELECT * FROM summit ORDER BY rating DESC")
    fun getAllsummits(): Flowable<List<Summit>>

    @Query("DELETE FROM summit")
    fun clearAllSummits()

//    @Query("SELECT * FROM appointmentNotification WHERE userId=:userId")
//    fun findAppointments(userId: Int): List<AppointmentNotification> {
//
//    }

    @Transaction
    fun updateAppointmentsList(appointmentNotifications: List<Summit>) {
        clearAllSummits()
        insertSummitsList(appointmentNotifications)
    }
}