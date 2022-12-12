package com.aasif.weatherforecasetmvvm.data.db.entity.accuWeatherResponse


import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("HasPrecipitation")
    val hasPrecipitation: Boolean,
    @SerializedName("Icon")
    val icon: Int,
    @SerializedName("IconPhrase")
    val iconPhrase: String
)