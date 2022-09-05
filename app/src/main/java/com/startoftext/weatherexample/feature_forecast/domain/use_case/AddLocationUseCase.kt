package com.startoftext.weatherexample.feature_forecast.domain.use_case

import com.startoftext.weatherexample.feature_forecast.domain.LocationRepository
import com.startoftext.weatherexample.feature_forecast.domain.model.InvalidLocationException
import com.startoftext.weatherexample.feature_forecast.domain.model.Location

class AddLocationUseCase(
    private val repository: LocationRepository
) {

    @Throws(InvalidLocationException::class)
    suspend operator fun invoke(location: Location){
        if(location.name.isBlank()){
            throw InvalidLocationException("The name of a location can't be empty.")
        }

        repository.insertLocation(location)
    }
}