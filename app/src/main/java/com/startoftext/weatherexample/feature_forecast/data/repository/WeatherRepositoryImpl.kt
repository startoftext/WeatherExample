package com.startoftext.weatherexample.feature_forecast.data.repository

import com.startoftext.weatherexample.feature_forecast.data.openweather.WeatherApi
import com.startoftext.weatherexample.feature_forecast.domain.WeatherRepository
import com.startoftext.weatherexample.feature_forecast.domain.model.FiveDayForecast
import com.startoftext.weatherexample.feature_forecast.domain.model.Weather
import com.startoftext.weatherexample.feature_forecast.domain.util.Resource
import com.startoftext.weatherexample.feature_forecast.domain.util.toFiveDayForecast
import com.startoftext.weatherexample.feature_forecast.domain.util.toWeather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override fun getFiveDayForecast(lat: Double, lon: Double): Flow<Resource<FiveDayForecast>> {
        return flow {
            emit(Resource.Loading(true))
            val response = weatherApi.getFiveDayForecast(lat = lat, lon = lon)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(Resource.Success(body.toFiveDayForecast()))
                emit(Resource.Loading(false))
            } else {
                emit(Resource.Error(response.message()))
                emit(Resource.Loading(false))
            }
        }
    }

    override fun getCurrentWeather(lat: Double, lon: Double): Flow<Resource<Weather>> {
        return flow {
            emit(Resource.Loading(true))
            val response = weatherApi.getCurrentWeather(lat = lat, lon = lon)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(Resource.Success(body.toWeather()))
            } else {
                emit(Resource.Error(response.message()))
            }
            emit(Resource.Loading(false))
        }

        // TODO cleanup
        //        val response = weatherApi.getCurrentWeather(lat = lat, lon = lon)
//        val body = response.body()
//        return if (response.isSuccessful && body != null) {
//            Resource.Success(body.toWeather())
//        } else {
//            Resource.Error(response.message())
//        }
    }
}