package com.startoftext.weatherexample.feature_forecast.presentation.locations

sealed class LocationsUiEvent {
    data class AddLocation(val name: String, val latitude: Double, val longitude: Double) :
        LocationsUiEvent()

    object Refresh : LocationsUiEvent()
}
