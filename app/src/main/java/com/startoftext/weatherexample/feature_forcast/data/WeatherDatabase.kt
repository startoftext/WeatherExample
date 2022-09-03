package com.startoftext.weatherexample.feature_forcast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.startoftext.weatherexample.feature_forcast.domain.model.Location

@Database(
    entities = [Location::class],
    version = 1
)
//@TypeConverters(Converters::class)
abstract class WeatherDatabase : RoomDatabase(){

    abstract val locationDao: LocationDao

    companion object {
        const val DATABASE_NAME = "weather_database"
    }
}