package com.startoftext.weatherexample.feature_forecast.presentation.weather_details.util

import com.startoftext.weatherexample.R

object WeatherIconResolver {

    fun resolve(icon: String): Int {
        return when (icon) {
            "01d" -> R.drawable.sunny
            "01n" -> R.drawable.clear_night
            "02d" -> R.drawable.partly_cloudy_day
            "02n" -> R.drawable.nights_cloud
            "03d", "03n" -> R.drawable.cloudy
            "04d", "04n" -> R.drawable.cloudy
            "09d", "09n" -> R.drawable.rainy
            "10d", "10n" -> R.drawable.rainy
            "11d", "11n" -> R.drawable.thunderstorm
            "13d", "13n" -> R.drawable.snow
            "50d", "50n" -> R.drawable.fog

            else -> R.drawable.sunny
        }
    }

}