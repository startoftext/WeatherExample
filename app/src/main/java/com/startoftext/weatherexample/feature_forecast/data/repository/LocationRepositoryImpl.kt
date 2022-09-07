package com.startoftext.weatherexample.feature_forecast.data.repository

import com.startoftext.weatherexample.feature_forecast.data.LocationDao
import com.startoftext.weatherexample.feature_forecast.domain.LocationRepository
import com.startoftext.weatherexample.feature_forecast.domain.model.Location
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class LocationRepositoryImpl(
    private val locationDao: LocationDao
) : LocationRepository {
    override fun getLocations(): Flow<List<Location>> = locationDao.getLocations()
    override suspend fun getLocationById(id: Int): Location? = locationDao.getLocationById(id)
    override suspend fun insertLocation(location: Location) = locationDao.insertLocation(location)
    override suspend fun deleteLocation(location: Location) = locationDao.deleteLocation(location)
}