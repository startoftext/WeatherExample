package com.startoftext.weatherexample.feature_forecast.presentation.weather_details

import com.startoftext.weatherexample.feature_forecast.domain.model.Forecast
import com.startoftext.weatherexample.feature_forecast.domain.model.ThreeHourForecast
import java.time.LocalDate

data class WeatherDetailState(
    val loading: Boolean = false,
    val currentWeather: Forecast? = null,
    val fiveDayForecast: Map<LocalDate, List<ThreeHourForecast>> = emptyMap()
)
