package com.aasif.weatherforecasetmvvm.ui.weather.future.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aasif.weatherforecasetmvvm.data.db.repository.DailyForecastRepository

class FutureListWeatherViewModelFactory(
    private val dailyForecastRepository: DailyForecastRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FutureListWeatherViewModel(dailyForecastRepository) as T
    }
}