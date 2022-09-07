package com.startoftext.weatherexample.di

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.startoftext.weatherexample.feature_forecast.data.WeatherDatabase
import com.startoftext.weatherexample.feature_forecast.data.openweather.WeatherApi
import com.startoftext.weatherexample.feature_forecast.data.openweather.WeatherApiBuilder
import com.startoftext.weatherexample.feature_forecast.data.repository.LocationRepositoryImpl
import com.startoftext.weatherexample.feature_forecast.data.repository.WeatherRepositoryImpl
import com.startoftext.weatherexample.feature_forecast.domain.LocationRepository
import com.startoftext.weatherexample.feature_forecast.domain.WeatherRepository
import com.startoftext.weatherexample.feature_forecast.domain.use_case.*
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
        return databaseBuilder(
            app,
            WeatherDatabase::class.java,
            WeatherDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideLocationRepository(db: WeatherDatabase, weatherApi: WeatherApi): LocationRepository {
        return LocationRepositoryImpl(db.locationDao)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi)
    }

    @Provides
    @Singleton
    fun provideLocationUseCases(
        locationRepository: LocationRepository,
        weatherRepository: WeatherRepository
    ): UseCases {
        return UseCases(
            getLocations = GetLocationsUseCase(locationRepository),
            deleteLocation = DeleteLocationUseCase(locationRepository),
            addLocation = AddLocationUseCase(locationRepository),
            getLocation = GetLocationUseCase(locationRepository),
            getLocationsAndForecast = GetLocationsAndWeatherUseCase(
                locationRepository,
                weatherRepository
            ),
            getFiveDayForecastUseCase = GetFiveDayForecastUseCase(weatherRepository),
            getCurrentWeatherUseCase = GetCurrentWeatherUseCase(weatherRepository)
        )
    }

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return WeatherApiBuilder.build()
    }
}