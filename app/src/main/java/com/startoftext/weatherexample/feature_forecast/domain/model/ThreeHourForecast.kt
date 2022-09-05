package com.startoftext.weatherexample.feature_forecast.domain.model

import java.util.*

data class ThreeHourForecast(
    val temp: Double,
    val weatherType: String,
    val description: String,
    val precipitationPercentage: Double,
    val date: Date
)
