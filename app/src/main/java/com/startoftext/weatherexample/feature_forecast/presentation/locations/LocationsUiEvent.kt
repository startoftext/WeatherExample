package com.startoftext.weatherexample.feature_forecast.presentation.locations

import com.startoftext.weatherexample.feature_forecast.domain.model.Location

sealed class LocationsUiEvent {
    data class AddLocation(val name: String, val latitude: Double, val longitude: Double) :
        LocationsUiEvent()
    data class DeleteLocation(val location: Location) : LocationsUiEvent()
    object Refresh : LocationsUiEvent()
}
