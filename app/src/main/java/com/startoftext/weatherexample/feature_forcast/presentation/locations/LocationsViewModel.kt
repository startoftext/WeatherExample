package com.startoftext.weatherexample.feature_forcast.presentation.locations

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.util.CollectionUtils.listOf
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.startoftext.weatherexample.BuildConfig
import com.startoftext.weatherexample.feature_forcast.domain.model.InvalidLocationException
import com.startoftext.weatherexample.feature_forcast.domain.model.Location
import com.startoftext.weatherexample.feature_forcast.domain.use_case.LocationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    @ApplicationContext applicationContext: Context,
    private val locationUseCases: LocationUseCases
): ViewModel() {
    private var getLocationsJob: Job? = null


    private val _state = mutableStateOf(LocationsState())
    val state: State<LocationsState> = _state

    val field = listOf(Place.Field.NAME, Place.Field.LAT_LNG)

    init {
        getLocations()
    }



    fun onEvent(event: LocationsEvent){
        when(event){
            is LocationsEvent.AddLocation -> {
                viewModelScope.launch {
                    try {
                        locationUseCases.addLocation.invoke(Location(event.name, event.longitude, event.latitude))
                        // TODO we could emit an event to the UI if needed
                    }catch (e: InvalidLocationException){
                        // TODO
//                        _eventFlow.emit(
//                            UiEvent.ShowSnackbar(
//                                message = e.message ?: "Couldn't save note"
//                            )
//                        )
                    }
                }
            }
            LocationsEvent.Refresh -> getLocations()
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