package com.aasif.weatherforecasetmvvm.data.db.entity.accuWeatherLocationResponse


import com.google.gson.annotations.SerializedName

data class TimeZone(
    @SerializedName("Code")
    val code: String,
    @SerializedName("GmtOffset")
    val gmtOffset: Double,
    @SerializedName("IsDaylightSaving")
    val isDaylightSaving: Boolean,
    @SerializedName("Name")
    val name: String,
    @SerializedName("NextOffsetChange")
    val nextOffsetChange: String
)