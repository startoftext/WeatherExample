package com.startoftext.weatherexample.feature_forecast.domain

import com.startoftext.weatherexample.feature_forecast.domain.model.FiveDayForecast
import com.startoftext.weatherexample.feature_forecast.domain.model.LatLon
import com.startoftext.weatherexample.feature_forecast.domain.model.Weather
import com.startoftext.weatherexample.feature_forecast.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getFiveDayForecast(lat: Double, lon: Double): Flow<Resource<FiveDayForecast>>
    fun getCurrentWeather(lat: Double, lon: Double): Flow<Resource<Weather>>
    fun getCurrentWeather(latLon: List<LatLon>): Flow<Resource<List<Weather?>>>
}