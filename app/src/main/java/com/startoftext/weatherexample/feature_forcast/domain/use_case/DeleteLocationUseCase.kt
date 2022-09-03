package com.startoftext.weatherexample.feature_forcast.domain.use_case

import com.startoftext.weatherexample.feature_forcast.domain.LocationRepository
import com.startoftext.weatherexample.feature_forcast.domain.model.Location

class DeleteLocationUseCase(private val repository: LocationRepository) {
    suspend operator fun invoke(location: Location){
        repository.deleteLocation(location)
    }
}