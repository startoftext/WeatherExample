package com.startoftext.weatherexample.feature_forecast.presentation.weather_details

sealed class WeatherDetailEvent {
    object Refresh : WeatherDetailEvent()
}