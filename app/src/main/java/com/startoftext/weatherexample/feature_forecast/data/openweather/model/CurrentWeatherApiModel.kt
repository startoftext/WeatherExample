package com.startoftext.weatherexample.feature_forecast.data.openweather.model

data class CurrentWeatherApiModel(
    val main: TempApiModel,
    val weather: List<WeatherApiModel>
)

