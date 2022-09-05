package com.startoftext.weatherexample.feature_forecast.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Forecast(
    val locationId: Int,
    val tempCurrent: Double,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)