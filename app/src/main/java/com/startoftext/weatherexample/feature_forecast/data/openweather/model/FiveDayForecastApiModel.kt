package com.startoftext.weatherexample.feature_forecast.data.openweather.model

import com.google.gson.annotations.SerializedName

data class FiveDayForecastApiModel(
    val list: List<ThreeHourForecast>
)

data class ThreeHourForecast(
    val main: TempApiModel,
    val weather: List<WeatherApiModel>,
    val rain: Rain?,
    val dt: Long
)

data class Rain(
    @SerializedName("3h")
    val precipitationPercentage: Double
)

