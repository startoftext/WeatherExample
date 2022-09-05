package com.startoftext.weatherexample.feature_forecast.data;

import androidx.room.*
import com.startoftext.weatherexample.feature_forecast.domain.model.Location;
import kotlinx.coroutines.flow.Flow;

@Dao
interface LocationDao {

    @Query("select * from location")
    fun getLocations(): Flow<List<Location>>

//    @Query("select * from location")
//    fun getLocationsAndForecast(): Flow<List<LocationAndForecast>>

    @Query("select * from location where id = :id")
    suspend fun getLocationById(id: Int): Location?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: Location)

    @Delete
    suspend fun deleteLocation(location: Location)

}
