package com.startoftext.weatherexample.feature_forecast.domain.use_case

import com.startoftext.weatherexample.feature_forecast.domain.LocationRepository
import com.startoftext.weatherexample.feature_forecast.domain.WeatherRepository
import com.startoftext.weatherexample.feature_forecast.domain.model.LatLon
import com.startoftext.weatherexample.feature_forecast.domain.model.LocationAndWeather
import com.startoftext.weatherexample.feature_forecast.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLocationsAndWeatherUseCase(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(): Flow<Resource<List<LocationAndWeather>>> {
        return flow {
            locationRepository.getLocations().collect() { locations ->
                weatherRepository.getCurrentWeather(
                    locations.map { location ->
                        LatLon(lat = location.latitude, lon = location.longitude)
                    }
                ).collect() { resource ->
                    when (resource) {
                        is Resource.Error -> emit(Resource.Error(resource.message!!))
                        is Resource.Loading -> emit(Resource.Loading(resource.isLoading))
                        is Resource.Success -> {
                            resource.data?.let { weatherList ->
                                emit(
                                    Resource.Success(
                                        locations.zip(weatherList).map {
                                            LocationAndWeather(it.first, it.second)
                                        })
                                )
                            }

                        }
                    }
                }
            }
        }
    }

}