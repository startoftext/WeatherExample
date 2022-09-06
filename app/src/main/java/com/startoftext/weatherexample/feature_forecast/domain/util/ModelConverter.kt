package com.startoftext.weatherexample.feature_forecast.domain.util

import com.startoftext.weatherexample.feature_forecast.data.openweather.model.CurrentWeatherApiModel
import com.startoftext.weatherexample.feature_forecast.data.openweather.model.FiveDayForecastApiModel
import com.startoftext.weatherexample.feature_forecast.domain.model.FiveDayForecast
import com.startoftext.weatherexample.feature_forecast.domain.model.ThreeHourForecast
import com.startoftext.weatherexample.feature_forecast.domain.model.Weather
import java.util.*

fun FiveDayForecastApiModel.toFiveDayForecast() = FiveDayForecast(
    this.list.map {
        ThreeHourForecast(
            temp = it.main.temp,
            weatherType = it.weather[0].main,
            description = it.weather[0].description,
            icon = it.weather[0].icon,
            precipitationPercentage = it.rain?.precipitationPercentage ?: 0.0,
            date = Date(it.dt * 1000)
        )
    }
)

fun CurrentWeatherApiModel.toWeather() = Weather(
    description = this.weather[0].description,
    temp = this.main.temp,
    weatherType = this.weather[0].main,
    icon = this.weather[0].icon
)
