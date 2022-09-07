package com.startoftext.weatherexample.feature_forecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.startoftext.weatherexample.feature_forecast.domain.model.Location
import com.startoftext.weatherexample.feature_forecast.domain.model.Weather

@Database(
    entities = [Location::class, Weather::class],
    version = 3
)
abstract class WeatherDatabase : RoomDatabase(){

    abstract val locationDao: LocationDao
    abstract val forecastDao: ForecastDao

    companion object {
        const val DATABASE_NAME = "weather_database"
    }
}