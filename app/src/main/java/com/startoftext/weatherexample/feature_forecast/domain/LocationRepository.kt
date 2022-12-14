package com.startoftext.weatherexample.feature_forecast.domain

import com.startoftext.weatherexample.feature_forecast.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocations(): Flow<List<Location>>
    suspend fun getLocationById(id: Int): Location?
    suspend fun insertLocation(location: Location)
    suspend fun deleteLocation(location: Location)
}