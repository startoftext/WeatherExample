package com.startoftext.weatherexample.feature_forcast.domain.use_case

import com.startoftext.weatherexample.feature_forcast.domain.LocationRepository
import com.startoftext.weatherexample.feature_forcast.domain.model.Location

class GetLocationUseCase(
    private val repository: LocationRepository
) {

    suspend operator fun invoke(id: Int): Location? {
        return repository.getLocationById(id)
    }
}