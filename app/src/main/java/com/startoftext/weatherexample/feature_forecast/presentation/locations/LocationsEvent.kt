package com.startoftext.weatherexample.feature_forecast.presentation.locations

import com.startoftext.weatherexample.feature_forecast.domain.model.Location

sealed class LocationsEvent {
    data class AddLocation(val name: String, val latitude: Double, val longitude: Double) :
        LocationsEvent()

    data class DeleteLocation(val location: Location) : LocationsEvent()
    object Refresh : LocationsEvent()
    object RestoreLocation : LocationsEvent()
}
