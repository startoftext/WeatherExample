package com.startoftext.weatherexample.feature_forecast.domain.use_case

import com.startoftext.weatherexample.feature_forecast.domain.WeatherRepository

data class GetCurrentWeatherUseCase(
    private val repository: WeatherRepository
) {
    operator fun invoke(lat: Double, lon: Double) =
        repository.getCurrentWeather(lat = lat, lon = lon)
}