package com.startoftext.weatherexample.feature_forecast.presentation.locations

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.util.CollectionUtils.listOf
import com.google.android.libraries.places.api.model.Place
import com.startoftext.weatherexample.feature_forecast.domain.model.InvalidLocationException
import com.startoftext.weatherexample.feature_forecast.domain.model.Location
import com.startoftext.weatherexample.feature_forecast.domain.use_case.LocationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val locationUseCases: LocationUseCases
): ViewModel() {
    private val _state = mutableStateOf(LocationsState())
    val state: State<LocationsState> = _state

    private var getLocationsJob: Job? = null

    val field = listOf(Place.Field.NAME, Place.Field.LAT_LNG)

    init {
        getLocations()
    }


    fun onEvent(event: LocationsUiEvent) {
        when (event) {
            is LocationsUiEvent.AddLocation -> {
                viewModelScope.launch {
                    try {
                        locationUseCases.addLocation.invoke(
                            Location(
                                event.name,
                                event.longitude,
                                event.latitude
                            )
                        )
                        // TODO we could emit an event to the UI if needed
                    } catch (e: InvalidLocationException) {
                        // TODO
//                        _eventFlow.emit(
//                            UiEvent.ShowSnackbar(
//                                message = e.message ?: "Couldn't save note"
//                            )
//                        )
                    }
                }
            }
            LocationsUiEvent.Refresh -> getLocations()
        }
    }

    private fun getLocations(){
        getLocationsJob?.cancel()
        getLocationsJob = locationUseCases.getLocationsAndForecast()
            .onEach {
                _state.value = state.value.copy(
                    locations = it
                )
            }
            .launchIn(viewModelScope)
    }

}