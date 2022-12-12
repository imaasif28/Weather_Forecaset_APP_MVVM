package com.aasif.weatherforecasetmvvm.ui.weather.future.list

import androidx.lifecycle.ViewModel
import com.aasif.weatherforecasetmvvm.data.db.repository.DailyForecastRepository
import com.aasif.weatherforecasetmvvm.internal.lazyDeferred

class FutureListWeatherViewModel(
    private val dailyForecastRepository: DailyForecastRepository
) : ViewModel() {
    val forecastWeather by lazyDeferred {
        dailyForecastRepository.getDailyForecast()
    }
}
