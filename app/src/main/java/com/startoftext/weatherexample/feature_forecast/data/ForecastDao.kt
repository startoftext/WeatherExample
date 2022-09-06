package com.startoftext.weatherexample.feature_forecast.data

import androidx.room.Dao
import androidx.room.Query
import com.startoftext.weatherexample.feature_forecast.domain.model.Weather

@Dao
interface ForecastDao {
    @Query("select * from weather where locationId = :locationId")
    suspend fun getForecastByLocationId(locationId: Int): Weather?
}