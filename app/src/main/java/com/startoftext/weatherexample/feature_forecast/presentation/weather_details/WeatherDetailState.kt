package com.startoftext.weatherexample.feature_forecast.presentation.weather_details

import com.startoftext.weatherexample.feature_forecast.domain.model.ThreeHourForecast
import com.startoftext.weatherexample.feature_forecast.domain.model.Weather
import java.time.LocalDate

data class WeatherDetailState(
    val loadingWeather: Boolean = false,
    val loadingForecast: Boolean = false,
    val title: String = "",
    val currentWeather: Weather? = null,
    val fiveDayForecast: Map<LocalDate, List<ThreeHourForecast>> = emptyMap(),
    val currentDate: String = ""
)
