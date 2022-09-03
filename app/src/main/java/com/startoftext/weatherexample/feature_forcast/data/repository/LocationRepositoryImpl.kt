package com.startoftext.weatherexample.feature_forcast.data.repository

import com.startoftext.weatherexample.feature_forcast.data.LocationDao
import com.startoftext.weatherexample.feature_forcast.domain.LocationRepository
import com.startoftext.weatherexample.feature_forcast.domain.model.Location
import kotlinx.coroutines.flow.Flow

class LocationRepositoryImpl(private val dao: LocationDao) : LocationRepository {
    override fun getLocations(): Flow<List<Location>> = dao.getLocations()

    override suspend fun getLocationById(id: Int): Location? = dao.getLocationById(id)

    override suspend fun insertLocation(location: Location) {
        dao.insertLocation(location)
    }

    override suspend fun deleteLocation(location: Location) {
        dao.deleteLocation(location)
    }
}