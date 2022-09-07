package com.startoftext.weatherexample.feature_forecast.presentation.locations

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.util.CollectionUtils.listOf
import com.google.android.libraries.places.api.model.Place
import com.startoftext.weatherexample.feature_forecast.domain.model.InvalidLocationException
import com.startoftext.weatherexample.feature_forecast.domain.model.Location
import com.startoftext.weatherexample.feature_forecast.domain.use_case.UseCases
import com.startoftext.weatherexample.feature_forecast.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val locationUseCases: UseCases
) : ViewModel() {
    private val _state = mutableStateOf(LocationsState())
    val state: State<LocationsState> = _state

    private var getLocationsJob: Job? = null
    private var getTemperatureJobs = mutableListOf<Job>()
    private var getTemperatureLoadingStates = mutableListOf<Boolean>()

    val field = listOf(Place.Field.NAME, Place.Field.LAT_LNG)

    init {
        getLocations()
    }
    
    fun onEvent(event: LocationsUiEvent) {
        when (event) {
            is LocationsUiEvent.AddLocation -> {
                viewModelScope.launch {
                    try {
                        locationUseCases.addLocation(
                            Location(
                                event.name,
                                event.longitude,
                                event.latitude
                            )
                        )
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
            is LocationsUiEvent.DeleteLocation -> viewModelScope.launch {
                locationUseCases.deleteLocation(
                    event.location
                )
            }
        }
    }

    private fun getLocations() {
        getLocationsJob?.cancel()
        getLocationsJob = locationUseCases.getLocationsAndForecast()
            .onEach {
                when (it) {
                    is Resource.Error -> TODO()
                    is Resource.Loading -> _state.value = state.value.copy(loading = it.isLoading)
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            locations = it.data!!
                        )
                    }
                }

            }.launchIn(viewModelScope)
    }
}