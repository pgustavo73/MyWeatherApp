package com.pgustavo.openweather

import android.app.Application
import com.pgustavo.openweather.common.data.local.databaseModule
import com.pgustavo.openweather.common.presentation.module.networkModule
import com.pgustavo.openweather.currentWeather.presentation.module.currentWeatherModule
import com.pgustavo.openweather.forecast.presentation.module.detailsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    currentWeatherModule,
                    detailsModule
                )
            )
        }
    }
}