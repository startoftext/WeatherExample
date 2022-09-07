package com.startoftext.weatherexample.feature_forecast.presentation.weather_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startoftext.weatherexample.feature_forecast.domain.model.Location
import com.startoftext.weatherexample.feature_forecast.domain.use_case.UseCases
import com.startoftext.weatherexample.feature_forecast.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    private val locationUseCases: UseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(WeatherDetailState())
    val state: State<WeatherDetailState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentLocation: Location? = null

    private val dateFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a").withZone(ZoneId.systemDefault())

    init {
        savedStateHandle.get<Int>("locationId")?.let { locationId ->
            if (locationId != -1) {
                viewModelScope.launch {
                    locationUseCases.getLocation(locationId)?.also { location ->
                        currentLocation = location
                        _state.value = state.value.copy(title = location.name)
                        refresh()
                    }
                }
            }
        }
    }

    fun onEvent(event: WeatherDetailEvent) {
        when (event) {
            WeatherDetailEvent.Refresh -> refresh()
        }
    }

    private fun refresh() {
        val location = currentLocation
        if (location != null) {
            locationUseCases.getFiveDayForecastUseCase(
                lat = location.latitude,
                lon = location.longitude
            ).onEach {
                when (it) {
                    is Resource.Error -> _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            message = it.message ?: "Oops! Something bad happened"
                        )
                    )
                    is Resource.Loading -> _state.value =
                        state.value.copy(loadingForecast = it.isLoading)
                    is Resource.Success -> {
                        val groups = it.data!!.threeHourForecasts.groupBy { forecast ->
                            forecast.date.toInstant().atZone(ZoneId.systemDefault())
                                .toLocalDate()
                        }
                        _state.value = state.value.copy(fiveDayForecast = groups)
                    }
                }
            }.launchIn(viewModelScope)

            locationUseCases.getCurrentWeatherUseCase(
                lat = location.latitude,
                lon = location.longitude
            ).onEach {
                when (it) {
                    is Resource.Error -> _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            message = it.message ?: "Oops! Something bad happened"
                        )
                    )
                    is Resource.Loading -> _state.value =
                        state.value.copy(loadingForecast = it.isLoading)
                    is Resource.Success -> _state.value =
                        state.value.copy(
                            currentWeather = it.data,
                            currentDate = dateFormatter.format(Date().toInstant())
                        )
                }
            }.launchIn(viewModelScope)
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
    }
}