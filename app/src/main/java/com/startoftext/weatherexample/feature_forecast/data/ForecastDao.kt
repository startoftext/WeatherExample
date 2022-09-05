package com.startoftext.weatherexample.feature_forecast.data

import androidx.room.Dao
import androidx.room.Query
import com.startoftext.weatherexample.feature_forecast.domain.model.Forecast

@Dao
interface ForecastDao {
    @Query("select * from forecast where locationId = :locationId")
    suspend fun getForecastByLocationId(locationId: Int): Forecast?
}