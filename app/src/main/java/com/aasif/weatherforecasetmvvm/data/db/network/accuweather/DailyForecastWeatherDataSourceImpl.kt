package com.aasif.weatherforecasetmvvm.data.db.network.accuweather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aasif.weatherforecasetmvvm.data.AccuWeatherApiService
import com.aasif.weatherforecasetmvvm.internal.NoConnectivityException

const val MAGICAL_ZERO = 0

class DailyForecastWeatherDataSourceImpl(
    private val accuWeatherApiService: AccuWeatherApiService
) : DailyForecastWeatherDataSource {
    private val _downloadedForecastWeather = MutableLiveData<AccuWeatherResponse>()
    override val downloadedForecastWeather: LiveData<AccuWeatherResponse>
        get() = _downloadedForecastWeather

    override suspend fun fetchForecastWeather(location: String, isMetric: String) {
        val locationFromApi = fetchLocationFromApi(location)
        if (locationFromApi != "") {
            try {
                val fetchForecastWeather =
                    accuWeatherApiService.getForecastAsync(locationFromApi, isMetric)
                        .await()
                _downloadedForecastWeather.postValue(fetchForecastWeather)
            } catch (e: NoConnectivityException) {
                Log.e("Connectivity", "No internet connection.", e)
            }
        }

    }

    override suspend fun fetchLocationFromApi(locationQuery: String): String {
        return try {
            val locationsFromApi = accuWeatherApiService.getLocationFromApiAsync(locationQuery)
                .await().toList()
            locationsFromApi[MAGICAL_ZERO].key
        } catch (e: Exception) {
            println("fetchLocationFromApi() : ${e.message}")
            ""
        }
    }
}