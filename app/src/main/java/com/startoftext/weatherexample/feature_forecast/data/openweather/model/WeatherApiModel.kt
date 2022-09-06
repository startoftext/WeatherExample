package com.startoftext.weatherexample.feature_forecast.data.openweather.model

data class WeatherApiModel(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)