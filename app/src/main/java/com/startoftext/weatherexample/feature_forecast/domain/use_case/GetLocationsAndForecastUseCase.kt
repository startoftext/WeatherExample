package com.startoftext.weatherexample.feature_forecast.domain.use_case

import com.startoftext.weatherexample.feature_forecast.domain.LocationRepository
import com.startoftext.weatherexample.feature_forecast.domain.model.LocationAndForecast
import kotlinx.coroutines.flow.Flow

class GetLocationsAndForecastUseCase(
    private val repository: LocationRepository
) {
    operator fun invoke(): Flow<List<LocationAndForecast>> {
        return repository.getLocationsAndForecast()
    }
}