package com.aasif.weatherforecasetmvvm.data.db.repository

import androidx.lifecycle.LiveData
import com.aasif.weatherforecasetmvvm.data.db.repository.DailyForecastRepository
import com.aasif.weatherforecasetmvvm.data.db.ForecastDao
import com.aasif.weatherforecasetmvvm.data.db.network.accuweather.AccuWeatherResponse
import com.aasif.weatherforecasetmvvm.data.db.network.accuweather.DailyForecastWeatherDataSource
import com.aasif.weatherforecasetmvvm.data.provider.LocationProvider
import com.aasif.weatherforecasetmvvm.data.provider.UnitProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DailyForecastRepositoryImpl(
    private val forecastDao: ForecastDao,
    private val dailyForecastWeatherDataSource: DailyForecastWeatherDataSource,
    private val locationProvider: LocationProvider,
    private val unitProvider: UnitProvider
) : DailyForecastRepository {

    init {
        dailyForecastWeatherDataSource.downloadedForecastWeather.observeForever {
            persistFetchedDailyForecast(it)
        }
    }

    private fun persistFetchedDailyForecast(accuWeatherResponse: AccuWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            forecastDao.upsert(accuWeatherResponse)
        }
    }

    override suspend fun getDailyForecast(): LiveData<AccuWeatherResponse> {
        return withContext(Dispatchers.IO){
            initForecastData()
            return@withContext forecastDao.getForecast()
        }
    }

    private suspend fun initForecastData() {
        if (isFetchNeeded()){
            fetchForecastWeather()
        }
    }

    private suspend fun fetchForecastWeather() {
        dailyForecastWeatherDataSource.fetchForecastWeather(
            locationProvider.getPreferredLocationString(),
            isMetric()
        )
    }

    private fun isMetric(): String {
        return if (unitProvider.getUnitSystem() == "m")
            "true"
        else
            "false"
    }

    private fun isFetchNeeded(): Boolean {
        //todo make it logical
        return true
    }
}