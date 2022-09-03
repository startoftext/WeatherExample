package com.startoftext.weatherexample.di

import android.app.Application
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.startoftext.weatherexample.feature_forcast.data.WeatherDatabase
import com.startoftext.weatherexample.feature_forcast.data.repository.LocationRepositoryImpl
import com.startoftext.weatherexample.feature_forcast.domain.LocationRepository
import com.startoftext.weatherexample.feature_forcast.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocationDatabase(app: Application): WeatherDatabase {
        return Room.databaseBuilder(
            app,
            WeatherDatabase::class.java,
            WeatherDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocationRepository(db: WeatherDatabase): LocationRepository{
        return LocationRepositoryImpl(db.locationDao)
    }

    @Provides
    @Singleton
    fun provideLocationUseCases(repository: LocationRepository): LocationUseCases {
        return LocationUseCases(
            getLocations = GetLocationsUseCase(repository),
            deleteLocation = DeleteLocationUseCase(repository),
            addLocation = AddLocationUseCase(repository),
            getLocation = GetLocationUseCase(repository)
        )
    }
}