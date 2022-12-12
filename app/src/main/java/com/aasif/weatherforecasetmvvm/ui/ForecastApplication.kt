package com.aasif.weatherforecasetmvvm.ui

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.aasif.weatherforecasetmvvm.R
import com.aasif.weatherforecasetmvvm.data.AccuWeatherApiService
import com.aasif.weatherforecasetmvvm.data.ApixuWeatherApiService
import com.aasif.weatherforecasetmvvm.data.db.ForecastDatabase
import com.aasif.weatherforecasetmvvm.data.db.network.ConnectivityInterceptor
import com.aasif.weatherforecasetmvvm.data.db.network.ConnectivityInterceptorImpl
import com.aasif.weatherforecasetmvvm.data.db.network.accuweather.DailyForecastWeatherDataSource
import com.aasif.weatherforecasetmvvm.data.db.network.accuweather.DailyForecastWeatherDataSourceImpl
import com.aasif.weatherforecasetmvvm.data.db.network.response.WeatherNetworkDataSource
import com.aasif.weatherforecasetmvvm.data.db.network.response.WeatherNetworkDataSourceImpl
import com.aasif.weatherforecasetmvvm.data.db.repository.DailyForecastRepository
import com.aasif.weatherforecasetmvvm.data.db.repository.DailyForecastRepositoryImpl
import com.aasif.weatherforecasetmvvm.data.db.repository.ForecastRepository
import com.aasif.weatherforecasetmvvm.data.db.repository.ForecastRepositoryImpl
import com.aasif.weatherforecasetmvvm.data.provider.LocationProvider
import com.aasif.weatherforecasetmvvm.data.provider.LocationProviderImpl
import com.aasif.weatherforecasetmvvm.data.provider.UnitProvider
import com.aasif.weatherforecasetmvvm.data.provider.UnitProviderImpl
import com.aasif.weatherforecasetmvvm.ui.weather.current.CurrentWeatherViewModelFactory
import com.aasif.weatherforecasetmvvm.ui.weather.future.list.FutureListWeatherViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class ForecastApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))
        this.bind<ForecastDatabase>() with this.singleton { ForecastDatabase(this.instance()) }
        this.bind<ConnectivityInterceptor>() with this.singleton { ConnectivityInterceptorImpl(this.instance()) }
        this.bind<ApixuWeatherApiService>() with this.singleton { ApixuWeatherApiService(this.instance()) }
        this.bind<UnitProvider>() with this.singleton { UnitProviderImpl(this.instance()) }
        this.bind<FusedLocationProviderClient>() with this.singleton {
            LocationServices.getFusedLocationProviderClient(
                this.instance<Context>()
            )
        }
        this.bind<LocationProvider>() with this.singleton {
            LocationProviderImpl(
                this.instance(),
                this.instance()
            )
        }
        this.bind<WeatherNetworkDataSource>() with this.singleton {
            WeatherNetworkDataSourceImpl(
                this.instance()
            )
        }
        this.bind<ForecastRepository>() with this.singleton {
            ForecastRepositoryImpl(
                this.instance<ForecastDatabase>().currentWeatherDao(),
                this.instance<ForecastDatabase>().weatherLocationDao(),
                this.instance(),
                this.instance(),
                this.instance()
            )
        }
        this.bind<CurrentWeatherViewModelFactory>() with this.singleton {
            CurrentWeatherViewModelFactory(
                this.instance()
            )
        }
        this.bind<AccuWeatherApiService>() with this.singleton { AccuWeatherApiService(this.instance()) }
        this.bind<DailyForecastWeatherDataSource>() with this.singleton {
            DailyForecastWeatherDataSourceImpl(
                this.instance()
            )
        }
        this.bind<DailyForecastRepository>() with this.singleton {
            DailyForecastRepositoryImpl(
                this.instance<ForecastDatabase>().forecastDao(),
                this.instance(),
                this.instance(),
                this.instance()
            )
        }
        this.bind<FutureListWeatherViewModelFactory>() with this.singleton {
            FutureListWeatherViewModelFactory(
                this.instance()
            )
        }

    }

    override fun onCreate() {
        super.onCreate()
        PreferenceManager.setDefaultValues(this, R.xml.prefs, true)
    }

}