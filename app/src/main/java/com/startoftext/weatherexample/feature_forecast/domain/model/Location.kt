package com.startoftext.weatherexample.feature_forecast.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class Location(
    val name: String,
    val longitude: Double,
    val latitude: Double,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)

class InvalidLocationException(message: String): Exception(message)