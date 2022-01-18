package com.pgustavo.openweather.currentWeather.data.local

import android.util.Log
import com.pgustavo.openweather.currentWeather.domain.repositories.CurrentWeatherLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class CurrentWeatherLocalDataSourceImpl(
    private val dao: CurrentWeatherDao
) : CurrentWeatherLocalDataSource {

    override fun observeAllWeather(): Flow<List<CurrentWeatherData>> {
        return dao.observeAllWeather().flowOn(Dispatchers.IO)
    }

    override suspend fun getAllWeather(): List<CurrentWeatherData> {
        return withContext(Dispatchers.IO) {
            dao.getAllWeather()
        }
    }

    override suspend fun saveCurrentWeather(data: CurrentWeatherData) {
        withContext(Dispatchers.IO) {
            dao.insert(data)
        }
    }

    override suspend fun deleteCurrentWeather(data: CurrentWeatherData) {
        withContext(Dispatchers.IO) {
            dao.delete(data)
        }
    }
}