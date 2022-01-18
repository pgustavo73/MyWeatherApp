package com.pgustavo.openweather.common.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pgustavo.openweather.currentWeather.data.local.CurrentWeatherDao
import com.pgustavo.openweather.currentWeather.data.local.CurrentWeatherData
import org.koin.dsl.module

@Database(entities = [CurrentWeatherData::class], version = 6, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            WeatherDatabase::class.java,
            "openweatherdbase.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}