package com.startoftext.weatherexample.feature_forecast.presentation.locations

import com.startoftext.weatherexample.feature_forecast.domain.model.LocationAndWeather
import java.util.Collections.emptyList

data class LocationsState(
    val loading: Boolean = false,
    val locations: List<LocationAndWeather> = emptyList(),
    val locationTemps: Map<Int, Int> = emptyMap()
)