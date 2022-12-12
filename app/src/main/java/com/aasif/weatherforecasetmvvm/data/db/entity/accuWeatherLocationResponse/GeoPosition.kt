package com.aasif.weatherforecasetmvvm.data.db.entity.accuWeatherLocationResponse


import com.aasif.weatherforecasetmvvm.data.db.entity.accuWeatherLocationResponse.Elevation
import com.google.gson.annotations.SerializedName

data class GeoPosition(
    @SerializedName("Elevation")
    val elevation: Elevation,
    @SerializedName("Latitude")
    val latitude: Double,
    @SerializedName("Longitude")
    val longitude: Double
)