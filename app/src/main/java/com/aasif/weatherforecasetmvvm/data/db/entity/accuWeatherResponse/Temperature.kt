package com.aasif.weatherforecasetmvvm.data.db.entity.accuWeatherResponse


import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("Maximum")
    val maximum: Maximum,
    @SerializedName("Minimum")
    val minimum: Minimum
)