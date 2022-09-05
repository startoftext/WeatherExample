package com.startoftext.weatherexample.feature_forecast.presentation.util

sealed class Screen(val route: String){
    object LocationsScreen : Screen("locations_screen")
    object WeatherDetailScreen : Screen("weather_details_screen")
}

