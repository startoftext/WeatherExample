package com.startoftext.weatherexample.feature_forecast.domain

import com.startoftext.weatherexample.feature_forecast.domain.model.Location
import com.startoftext.weatherexample.feature_forecast.domain.model.LocationAndForecast
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocations(): Flow<List<Location>>
    fun getLocationsAndForecast(): Flow<List<LocationAndForecast>>
    suspend fun getLocationById(id: Int): Location?
    suspend fun insertLocation(location: Location)
    suspend fun deleteLocation(location: Location)
}