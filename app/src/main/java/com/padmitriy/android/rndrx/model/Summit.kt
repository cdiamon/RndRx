package com.padmitriy.android.rndrx.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Summit(
    val name: String,
    val height: Float,
    var rating: Float,
    val picture: String
) {
    @PrimaryKey(autoGenerate = true)
    var summitId: Int = 0
}
