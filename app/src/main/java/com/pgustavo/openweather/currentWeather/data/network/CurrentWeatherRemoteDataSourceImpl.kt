package com.pgustavo.openweather.currentWeather.data.network

import com.pgustavo.openweather.currentWeather.data.network.response.CurrentWeatherResponse
import com.pgustavo.openweather.currentWeather.domain.repositories.CurrentWeatherRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrentWeatherRemoteDataSourceImpl(
    private val weatherApi: CurrentWeatherApi
) : CurrentWeatherRemoteDataSource {

    override suspend fun fetchCurrentWeather(city: String): CurrentWeatherResponse {
        return withContext(Dispatchers.IO) {
            weatherApi.fetchWeather(city)
        }
    }
}