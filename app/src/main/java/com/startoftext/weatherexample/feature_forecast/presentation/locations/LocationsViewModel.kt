package com.startoftext.weatherexample.feature_forecast.presentation.locations

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startoftext.weatherexample.feature_forecast.domain.model.InvalidLocationException
import com.startoftext.weatherexample.feature_forecast.domain.model.Location
import com.startoftext.weatherexample.feature_forecast.domain.use_case.UseCases
import com.startoftext.weatherexample.feature_forecast.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var getLocationsJob: Job? = null

    private var recentlyDeletedLocation: Location? = null;

    init {
        getLocations()
    }

    fun onEvent(event: LocationsEvent) {
        when (event) {
            is LocationsEvent.AddLocation -> {
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
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Oops! Something bad happened"
                            )
                        )
                    }
                }
            }
            LocationsEvent.Refresh -> getLocations()
            is LocationsEvent.DeleteLocation -> viewModelScope.launch {
                locationUseCases.deleteLocation(event.location)
                recentlyDeletedLocation = event.location
            }
            LocationsEvent.RestoreLocation -> {
                viewModelScope.launch {
                    locationUseCases.addLocation(recentlyDeletedLocation ?: return@launch)
                    recentlyDeletedLocation = null
                }
            }
        }
    }

    private fun getLocations() {
        getLocationsJob?.cancel()
        getLocationsJob = locationUseCases.getLocationsAndForecast()
            .onEach {
                when (it) {
                    is Resource.Error -> _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            message = it.message ?: "Oops! Something bad happened"
                        )
                    )
                    is Resource.Loading -> _state.value = state.value.copy(loading = it.isLoading)
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            locations = it.data!!
                        )
                    }
                }

            }.launchIn(viewModelScope)
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
    }
}