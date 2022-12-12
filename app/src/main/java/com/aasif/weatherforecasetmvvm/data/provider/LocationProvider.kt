package com.aasif.weatherforecasetmvvm.data.provider

import com.aasif.weatherforecasetmvvm.data.db.entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocationString(): String
}