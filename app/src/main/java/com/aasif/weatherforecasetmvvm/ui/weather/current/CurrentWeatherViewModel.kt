package com.aasif.weatherforecasetmvvm.ui.weather.current

import androidx.lifecycle.ViewModel
import com.aasif.weatherforecasetmvvm.data.db.repository.ForecastRepository
import com.aasif.weatherforecasetmvvm.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather()
    }


    val location by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }
}
