package com.aasif.weatherforecasetmvvm.data.db.repository

import androidx.lifecycle.LiveData
import com.aasif.weatherforecasetmvvm.data.db.CurrentWeatherDao
import com.aasif.weatherforecasetmvvm.data.db.WeatherLocationDao
import com.aasif.weatherforecasetmvvm.data.db.entity.CurrentWeatherEntry
import com.aasif.weatherforecasetmvvm.data.db.entity.WeatherLocation
import com.aasif.weatherforecasetmvvm.data.db.network.response.CurrentWeatherResponse
import com.aasif.weatherforecasetmvvm.data.db.network.response.WeatherNetworkDataSource
import com.aasif.weatherforecasetmvvm.data.provider.LocationProvider
import com.aasif.weatherforecasetmvvm.data.provider.UnitProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Time
import java.util.*


class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherLocationDao: WeatherLocationDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val unitProvider: UnitProvider,
    private val locationProvider: LocationProvider
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever{
            persistFetchedCurrentWeather(it)
        }
    }


    override suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry> {
        return withContext(Dispatchers.IO){
            initWeatherData()
            return@withContext currentWeatherDao.getWeather()
        }
    }

    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO){
            return@withContext weatherLocationDao.getLocation()
        }
    }

    private fun persistFetchedCurrentWeather(currentWeatherResponse: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(currentWeatherResponse.currentWeatherEntry)
            weatherLocationDao.upsert(currentWeatherResponse.location)
        }
    }

    private suspend fun initWeatherData() {
        val lastWeatherLocation = weatherLocationDao.getLocation().value
        if (lastWeatherLocation == null){
            //todo this condition is always true fix this
            println("debug from initweather: lastWeatherLocation is null")
            fetchCurrentWeather()
            return
        }
        val temp = lastWeatherLocation
        if (isFetchCurrentNeeded(temp!!.zonedDateTime)
            || locationProvider.hasLocationChanged(temp!!))
            fetchCurrentWeather()
    }


    private suspend fun fetchCurrentWeather(){
        println("special debug: ${unitProvider.getUnitSystem()}")
        weatherNetworkDataSource.fetchCurrentWeather(
            //todo fetch data according to location from settings or current location
            locationProvider.getPreferredLocationString(),
            Locale.getDefault().language,
            unitProvider.getUnitSystem()
        )
    }

    private fun isFetchCurrentNeeded(lastWeatherResponseFetchTime: Time): Boolean {
        //TODO: local time and location time mismatch
        val thirtyMinAgoMillis = System.currentTimeMillis() - 60000 * 30
        val thirtyMinAgo = Time(thirtyMinAgoMillis)
        println("debug hh: ${thirtyMinAgo > lastWeatherResponseFetchTime}")
        return thirtyMinAgo > lastWeatherResponseFetchTime
    }
}