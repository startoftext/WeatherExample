package com.startoftext.weatherexample.feature_forecast.domain.use_case

data class UseCases(
    val getLocations: GetLocationsUseCase,
    val deleteLocation: DeleteLocationUseCase,
    val addLocation: AddLocationUseCase,
    val getLocation: GetLocationUseCase,
    val getLocationsAndForecast: GetLocationsAndForecastUseCase,
    val getFiveDayForecastUseCase: GetFiveDayForecastUseCase,
    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
)