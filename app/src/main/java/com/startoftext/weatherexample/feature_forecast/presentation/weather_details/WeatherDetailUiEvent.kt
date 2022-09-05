package com.startoftext.weatherexample.feature_forecast.presentation.weather_details

sealed class WeatherDetailUiEvent {
    object Refresh : WeatherDetailUiEvent()
}