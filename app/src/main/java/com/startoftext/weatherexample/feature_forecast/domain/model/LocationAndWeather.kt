package com.startoftext.weatherexample.feature_forecast.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class LocationAndWeather(
    @Embedded
    val location: Location,

    @Relation(parentColumn = "id", entityColumn = "locationId")
    val forecast: Weather?
)