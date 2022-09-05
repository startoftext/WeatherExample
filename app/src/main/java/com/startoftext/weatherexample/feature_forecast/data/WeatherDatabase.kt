package com.startoftext.weatherexample.feature_forecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.startoftext.weatherexample.feature_forecast.domain.model.Forecast
import com.startoftext.weatherexample.feature_forecast.domain.model.Location

@Database(
    entities = [Location::class, Forecast::class],
    version = 2
)
//@TypeConverters(Converters::class)
abstract class WeatherDatabase : RoomDatabase(){

    abstract val locationDao: LocationDao
    abstract val forecastDao: ForecastDao

    companion object {
        const val DATABASE_NAME = "weather_database"
    }
}