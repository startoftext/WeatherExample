package com.startoftext.weatherexample.feature_forecast.data.openweather.model

import com.google.gson.annotations.SerializedName

data class FiveDayForecastApiModel(
    val list: List<ThreeHourForecast>
)

data class ThreeHourForecast(
    val main: Main,
    val weather: List<Weather>,
    val rain: Rain?,
    val dt: Long
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Rain(
    @SerializedName("3h")
    val precipitationPercentage: Double
)

