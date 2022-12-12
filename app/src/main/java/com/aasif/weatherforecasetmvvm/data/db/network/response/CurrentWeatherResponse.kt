package com.aasif.weatherforecasetmvvm.data.db.network.response

import com.aasif.weatherforecasetmvvm.data.db.entity.CurrentWeatherEntry
import com.aasif.weatherforecasetmvvm.data.db.entity.WeatherLocation
import com.aasif.weatherforecasetmvvm.data.db.entity.Request
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: WeatherLocation,
    val request: Request
)