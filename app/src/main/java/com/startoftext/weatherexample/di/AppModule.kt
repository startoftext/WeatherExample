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
        return LocationRepositoryImpl(db.locationDao, db.forecastDao, weatherApi)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi)
    }

    @Provides
    @Singleton
    fun provideLocationUseCases(repository: LocationRepository): LocationUseCases {
        return LocationUseCases(
            getLocations = GetLocationsUseCase(repository),
            deleteLocation = DeleteLocationUseCase(repository),
            addLocation = AddLocationUseCase(repository),
            getLocation = GetLocationUseCase(repository),
            getLocationsAndForecast = GetLocationsAndForecastUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideWeatherUseCases(repository: WeatherRepository): WeatherUseCases {
        return WeatherUseCases(
            getFiveDayForecastUseCase = GetFiveDayForecastUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return WeatherApiBuilder.build()
    }
}