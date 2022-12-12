package com.aasif.weatherforecasetmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aasif.weatherforecasetmvvm.data.db.entity.CurrentWeatherEntry
import com.aasif.weatherforecasetmvvm.data.db.entity.WeatherLocation
import com.aasif.weatherforecasetmvvm.data.db.network.accuweather.AccuWeatherResponse
import com.aasif.weatherforecasetmvvm.internal.DataTypeConverter

@Database(
    entities = [CurrentWeatherEntry::class, WeatherLocation::class, AccuWeatherResponse::class],
    version = 1
)
@TypeConverters(DataTypeConverter::class)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun weatherLocationDao(): WeatherLocationDao
    abstract fun forecastDao(): ForecastDao

    companion object {
        @Volatile
        private var instance: ForecastDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ForecastDatabase::class.java, "forecast.db"
            ).build()
    }
}