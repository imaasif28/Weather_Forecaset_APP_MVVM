package com.aasif.weatherforecasetmvvm.data.db.repository

import androidx.lifecycle.LiveData
import com.aasif.weatherforecasetmvvm.data.db.entity.CurrentWeatherEntry
import com.aasif.weatherforecasetmvvm.data.db.entity.WeatherLocation

interface ForecastRepository {
    suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}