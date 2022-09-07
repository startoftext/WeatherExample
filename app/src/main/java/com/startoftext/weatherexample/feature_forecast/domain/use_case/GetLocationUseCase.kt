package com.startoftext.weatherexample.feature_forecast.domain.use_case

import com.startoftext.weatherexample.feature_forecast.domain.LocationRepository
import com.startoftext.weatherexample.feature_forecast.domain.model.Location

class GetLocationUseCase(
    private val repository: LocationRepository
) {
    suspend operator fun invoke(id: Int): Location? {
        return repository.getLocationById(id)
    }
}