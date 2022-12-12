package com.aasif.weatherforecasetmvvm.data.db.network.accuweather

import androidx.lifecycle.LiveData

interface DailyForecastWeatherDataSource {

    val downloadedForecastWeather: LiveData<AccuWeatherResponse>

    suspend fun fetchForecastWeather(location: String, isMetric: String)
    suspend fun fetchLocationFromApi(locationQuery: String): String
}