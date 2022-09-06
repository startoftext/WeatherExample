package com.startoftext.weatherexample.feature_forecast.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    val locationId: Int? = null,
    val temp: Double,
    val weatherType: String,
    val description: String,
    val icon: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)