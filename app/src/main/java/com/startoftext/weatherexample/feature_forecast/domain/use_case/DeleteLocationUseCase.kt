package com.startoftext.weatherexample.feature_forecast.domain.use_case

import com.startoftext.weatherexample.feature_forecast.domain.LocationRepository
import com.startoftext.weatherexample.feature_forecast.domain.model.Location

class DeleteLocationUseCase(private val repository: LocationRepository) {
    suspend operator fun invoke(location: Location){
        repository.deleteLocation(location)
    }
}