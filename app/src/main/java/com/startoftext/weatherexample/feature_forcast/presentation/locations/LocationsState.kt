package com.startoftext.weatherexample.feature_forcast.presentation.locations

import com.startoftext.weatherexample.feature_forcast.domain.model.Location
import com.startoftext.weatherexample.feature_forcast.domain.model.LocationAndForecast
import java.util.Collections.emptyList

data class LocationsState (
    val locations: List<LocationAndForecast> = emptyList()
)