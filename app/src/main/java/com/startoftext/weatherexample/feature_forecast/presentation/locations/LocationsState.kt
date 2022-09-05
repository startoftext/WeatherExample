package com.startoftext.weatherexample.feature_forecast.presentation.locations

import com.startoftext.weatherexample.feature_forecast.domain.model.LocationAndForecast
import java.util.Collections.emptyList

data class LocationsState (
    val locations: List<LocationAndForecast> = emptyList()
)