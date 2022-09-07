package com.startoftext.weatherexample.feature_forecast.data.openweather

import com.startoftext.weatherexample.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object WeatherApiBuilder {
    val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    fun build(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val url = chain.request().url.newBuilder()
                    .addQueryParameter("appid", BuildConfig.OPEN_WEATHER_API_KEY).build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}