package com.startoftext.weatherexample.feature_forecast.presentation.locations

import com.startoftext.weatherexample.feature_forecast.domain.model.Location
import java.util.Collections.emptyList

data class LocationsState(
    val locations: List<Location> = emptyList(),
    val locationTemps: Map<Int, Int> = emptyMap()
)