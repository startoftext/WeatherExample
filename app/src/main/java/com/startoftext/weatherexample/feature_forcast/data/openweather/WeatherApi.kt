package com.startoftext.weatherexample.feature_forcast.data.openweather

import com.startoftext.weatherexample.feature_forcast.data.openweather.model.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather?units=imperial")
    suspend fun getCurrentWeather(@Query("lat") lat: Double, @Query("lon") lon: Double): Response<CurrentWeather>
}