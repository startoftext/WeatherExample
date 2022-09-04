package com.startoftext.weatherexample.feature_forcast.data.repository

import com.startoftext.weatherexample.feature_forcast.data.ForecastDao
import com.startoftext.weatherexample.feature_forcast.data.LocationDao
import com.startoftext.weatherexample.feature_forcast.data.openweather.WeatherApi
import com.startoftext.weatherexample.feature_forcast.domain.LocationRepository
import com.startoftext.weatherexample.feature_forcast.domain.model.Forecast
import com.startoftext.weatherexample.feature_forcast.domain.model.Location
import com.startoftext.weatherexample.feature_forcast.domain.model.LocationAndForecast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocationRepositoryImpl(
    private val locationDao: LocationDao,
    private val forecastDao: ForecastDao,
    private val weatherApi: WeatherApi
    ) : LocationRepository {
    override fun getLocations(): Flow<List<Location>> = locationDao.getLocations()

    override fun getLocationsAndForecast(): Flow<List<LocationAndForecast>> {
        return locationDao.getLocations().map {
            it.map { location ->
                val forecastFromDb = location.id?.let { it1 -> forecastDao.getForecastByLocationId(it1) }

                val forecast : Forecast = if (forecastFromDb != null) {
                    forecastFromDb
                } else {
                    val response = weatherApi.getCurrentWeather(location.longitude, location.latitude)
                    val currentWeather = response.body()
                    val forecast = Forecast(location.id!!, currentWeather!!.main!!.temp)
                    forecast
                }

                LocationAndForecast(location, forecast)
            }
        }
    }

    override suspend fun getLocationById(id: Int): Location? = locationDao.getLocationById(id)

    override suspend fun insertLocation(location: Location) {
        locationDao.insertLocation(location)
    }

    override suspend fun deleteLocation(location: Location) {
        locationDao.deleteLocation(location)
    }
}