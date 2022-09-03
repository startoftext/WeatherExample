package com.startoftext.weatherexample.feature_forcast.presentation.util

sealed class Screen(val route: String){
    object LocationsScreen: Screen("locations_screen")
    object WeatherDetailsScreen: Screen("weather_details_screen")
}

