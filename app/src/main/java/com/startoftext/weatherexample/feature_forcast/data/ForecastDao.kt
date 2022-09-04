package com.startoftext.weatherexample.feature_forcast.data

import androidx.room.Dao
import androidx.room.Query
import com.startoftext.weatherexample.feature_forcast.domain.model.Forecast
import com.startoftext.weatherexample.feature_forcast.domain.model.Location

@Dao
interface ForecastDao {
    @Query("select * from forecast where locationId = :locationId")
    suspend fun getForecastByLocationId(locationId: Int): Forecast?
}