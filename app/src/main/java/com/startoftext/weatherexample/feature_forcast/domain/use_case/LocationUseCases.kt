package com.startoftext.weatherexample.feature_forcast.domain.use_case

data class LocationUseCases (
    val getLocations: GetLocationsUseCase,
    val deleteLocation: DeleteLocationUseCase,
    val addLocation: AddLocationUseCase,
    val getLocation: GetLocationUseCase
)