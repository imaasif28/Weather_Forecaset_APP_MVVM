package com.aasif.weatherforecasetmvvm.internal

import androidx.room.TypeConverter
import com.aasif.weatherforecasetmvvm.data.db.entity.accuWeatherResponse.DailyForecast
import com.aasif.weatherforecasetmvvm.data.db.entity.accuWeatherResponse.Day
import com.aasif.weatherforecasetmvvm.data.db.entity.accuWeatherResponse.Temperature
import com.google.gson.Gson

class DataTypeConverter {
    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun temperatureToJson(value: Temperature) = Gson().toJson(value)
    @TypeConverter
    fun jsonToTemperature(value: String) = Gson().fromJson(value, Temperature::class.java)

    @TypeConverter
    fun dayToJson(value: Day) = Gson().toJson(value)
    @TypeConverter
    fun jsonToDay(value: String) = Gson().fromJson(value, Day::class.java)


    @TypeConverter
    fun dailyForcastListToJson(value: List<DailyForecast>) = Gson().toJson(value)
    @TypeConverter
    fun jsonToDailyForecastList(value: String) = Gson().fromJson(value, Array<DailyForecast>::class.java).toList()
}