package com.aasif.weatherforecasetmvvm.data.db.entity.accuWeatherResponse


import com.google.gson.annotations.SerializedName


data class DailyForecast(
    @SerializedName("Date")
    val date: String,
    @SerializedName("Day")
    val day: Day,
    @SerializedName("EpochDate")
    val epochDate: Long,
    @SerializedName("Temperature")
    val temperature: Temperature
)
