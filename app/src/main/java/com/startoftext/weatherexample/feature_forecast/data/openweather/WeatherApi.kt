package com.startoftext.weatherexample.feature_forecast.data.openweather

import com.startoftext.weatherexample.feature_forecast.data.openweather.model.CurrentWeather
import com.startoftext.weatherexample.feature_forecast.data.openweather.model.FiveDayForecastApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather?units=imperial")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Response<CurrentWeather>

    @GET("forecast?units=imperial")
    suspend fun getFiveDayForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Response<FiveDayForecastApiModel>
}