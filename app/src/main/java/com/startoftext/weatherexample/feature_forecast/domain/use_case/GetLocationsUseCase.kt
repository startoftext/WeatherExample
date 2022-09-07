package com.startoftext.weatherexample.feature_forecast.domain.use_case

import com.startoftext.weatherexample.feature_forecast.domain.LocationRepository
import com.startoftext.weatherexample.feature_forecast.domain.model.Location
import kotlinx.coroutines.flow.Flow

class GetLocationsUseCase(
    private val repository: LocationRepository
) {
    operator fun invoke(): Flow<List<Location>> {
        return repository.getLocations()
    }
}