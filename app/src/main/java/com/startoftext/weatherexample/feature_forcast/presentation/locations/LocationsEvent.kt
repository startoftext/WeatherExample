package com.startoftext.weatherexample.feature_forcast.presentation.locations

sealed class LocationsEvent{
    data class AddLocation(val name: String, val latitude: Double, val longitude: Double) : LocationsEvent()
}
