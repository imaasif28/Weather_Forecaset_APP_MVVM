package com.aasif.weatherforecasetmvvm.data.db.repository

import androidx.lifecycle.LiveData
import com.aasif.weatherforecasetmvvm.data.db.network.accuweather.AccuWeatherResponse

interface DailyForecastRepository {
    suspend fun getDailyForecast(): LiveData<AccuWeatherResponse>
}