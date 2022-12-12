package com.aasif.weatherforecasetmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aasif.weatherforecasetmvvm.data.db.network.accuweather.AccuWeatherResponse

@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(accuWeatherResponse: AccuWeatherResponse)


    @Query("select * from accuWeather_table")
    fun getForecast(): LiveData<AccuWeatherResponse>
}