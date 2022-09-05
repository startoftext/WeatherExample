package com.startoftext.weatherexample.feature_forecast.domain.use_case

data class LocationUseCases (
    val getLocations: GetLocationsUseCase,
    val deleteLocation: DeleteLocationUseCase,
    val addLocation: AddLocationUseCase,
    val getLocation: GetLocationUseCase,
    val getLocationsAndForecast: GetLocationsAndForecastUseCase
)