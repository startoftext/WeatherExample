package com.startoftext.weatherexample.di

import android.app.Application
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.startoftext.weatherexample.feature_forcast.data.WeatherDatabase
import com.startoftext.weatherexample.feature_forcast.data.openweather.WeatherApi
import com.startoftext.weatherexample.feature_forcast.data.openweather.WeatherApiBuilder
import com.startoftext.weatherexample.feature_forcast.data.repository.LocationRepositoryImpl
import com.startoftext.weatherexample.feature_forcast.domain.LocationRepository
import com.startoftext.weatherexample.feature_forcast.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideLocationRepository(db: WeatherDatabase, weatherApi: WeatherApi): LocationRepository{
        return LocationRepositoryImpl(db.locationDao, db.forecastDao, weatherApi)
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
    fun provideWeatherApi(): WeatherApi{
        return WeatherApiBuilder.build()
    }
}