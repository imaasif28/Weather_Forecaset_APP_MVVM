package com.aasif.weatherforecasetmvvm.data.db.network.response

import androidx.lifecycle.LiveData
import com.aasif.weatherforecasetmvvm.data.db.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(location: String, languageCode: String, unit: String)
}