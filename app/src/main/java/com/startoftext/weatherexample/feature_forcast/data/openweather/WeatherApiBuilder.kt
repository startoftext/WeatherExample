package com.startoftext.weatherexample.feature_forcast.data.openweather

import com.startoftext.weatherexample.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object WeatherApiBuilder {
    fun build(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .client(OkHttpClient.Builder().addInterceptor{ chain ->
                val url = chain.request().url.newBuilder().addQueryParameter("appid", BuildConfig.OPEN_WEATHER_API_KEY).build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}