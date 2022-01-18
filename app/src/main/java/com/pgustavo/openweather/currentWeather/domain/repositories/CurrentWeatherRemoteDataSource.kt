package com.pgustavo.openweather.currentWeather.domain.repositories

import com.pgustavo.openweather.currentWeather.data.network.response.CurrentWeatherResponse

interface CurrentWeatherRemoteDataSource {
    suspend fun fetchCurrentWeather(city: String): CurrentWeatherResponse
}