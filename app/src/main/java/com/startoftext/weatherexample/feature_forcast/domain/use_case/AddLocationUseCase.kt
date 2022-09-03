package com.startoftext.weatherexample.feature_forcast.domain.use_case

import com.startoftext.weatherexample.feature_forcast.domain.LocationRepository
import com.startoftext.weatherexample.feature_forcast.domain.model.InvalidLocationException
import com.startoftext.weatherexample.feature_forcast.domain.model.Location
import kotlin.jvm.Throws

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