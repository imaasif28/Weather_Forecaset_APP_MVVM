package com.aasif.weatherforecasetmvvm.data.db.network.accuweather


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aasif.weatherforecasetmvvm.data.db.entity.accuWeatherResponse.DailyForecast
import com.google.gson.annotations.SerializedName

const val ID = 0
@Entity(tableName = "accuWeather_table")
data class AccuWeatherResponse(
    @SerializedName("DailyForecasts")
    val dailyForecasts: List<DailyForecast>
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = ID
}