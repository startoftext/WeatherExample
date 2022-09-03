package com.startoftext.weatherexample

import android.app.Application
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherExampleApp : Application(){
    override fun onCreate() {
        super.onCreate()
            Places.initialize(applicationContext, BuildConfig.PLACES_SDK_KEY)
    }
}