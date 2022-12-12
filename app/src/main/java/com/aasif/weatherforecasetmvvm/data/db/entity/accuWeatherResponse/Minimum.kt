package com.aasif.weatherforecasetmvvm.data.db.entity.accuWeatherResponse


import com.google.gson.annotations.SerializedName

data class Minimum(
    @SerializedName("Unit")
    val unit: String,
    @SerializedName("UnitType")
    val unitType: Int,
    @SerializedName("Value")
    val value: Double
)