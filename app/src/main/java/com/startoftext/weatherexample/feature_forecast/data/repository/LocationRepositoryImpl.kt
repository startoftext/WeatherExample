package com.startoftext.weatherexample.feature_forecast.data.repository

import com.startoftext.weatherexample.feature_forecast.data.ForecastDao
import com.startoftext.weatherexample.feature_forecast.data.LocationDao
import com.startoftext.weatherexample.feature_forecast.data.openweather.WeatherApi
import com.startoftext.weatherexample.feature_forecast.domain.LocationRepository
import com.startoftext.weatherexample.feature_forecast.domain.model.Location
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class LocationRepositoryImpl(
    private val locationDao: LocationDao,
    private val forecastDao: ForecastDao,
    private val weatherApi: WeatherApi
) : LocationRepository {
    override fun getLocations(): Flow<List<Location>> = locationDao.getLocations()

    //override fun getLocationsAndForecast(): Flow<Resource<List<LocationAndWeather>>> {
//        return flow {
//            locationDao.getLocations().map {
//                coroutineScope {
//
//                    val foo = it.map {
//                        async {
//                            weatherApi.getCurrentWeather(lat = it.latitude, lon = it.longitude)
//                        }
//                    }.awaitAll().map {
//                        val body = it.body()
//                        val x = if (it.isSuccessful && body != null) {
//                            body.toWeather()
//                        } else {
//                            emit(Resource.Error(it.message()))
//                            null
//                        }
//                    }
//
//
//                }
//
//            }

//            locationDao.getLocations().map {
//
//                it.map { location ->
//
//                    val forecastFromDb =
//                        location.id?.let { it1 -> forecastDao.getForecastByLocationId(it1) }
//
//                    val forecast: Weather = if (forecastFromDb != null) {
//                        // TODO fix this
//                        forecastFromDb
//                    } else {
//                        val response =
//                            weatherApi.getCurrentWeather(location.latitude, location.longitude)
//                        val currentWeather = response.body()
//                        val forecast = Weather(
//                            location.id!!,
//                            currentWeather!!.main!!.temp,
//                            icon = "",
//                            weatherType = "",
//                            description = ""
//                        )
//                        forecast
//                    }
//
//                    LocationAndWeather(location, forecast)
//                }
//            }


//        }
//    }

    override suspend fun getLocationById(id: Int): Location? = locationDao.getLocationById(id)

    override suspend fun insertLocation(location: Location) {
        locationDao.insertLocation(location)
    }

    override suspend fun deleteLocation(location: Location) {
        locationDao.deleteLocation(location)
    }
}