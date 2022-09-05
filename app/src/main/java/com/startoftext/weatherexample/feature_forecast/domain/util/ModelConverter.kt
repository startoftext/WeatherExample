package com.startoftext.weatherexample.feature_forecast.domain.util

import com.startoftext.weatherexample.feature_forecast.data.openweather.model.FiveDayForecastApiModel
import com.startoftext.weatherexample.feature_forecast.domain.model.FiveDayForecast
import com.startoftext.weatherexample.feature_forecast.domain.model.ThreeHourForecast
import java.util.*

fun FiveDayForecastApiModel.toFiveDayForecast(): FiveDayForecast {
    return FiveDayForecast(
        this.list.map {
            ThreeHourForecast(
                it.main.temp,
                it.weather[0].main,
                it.weather[0].description,
                it.rain?.precipitationPercentage ?: 0.0,
                Date(it.dt * 1000)
            )
        }
    )
}