package com.pgustavo.openweather.forecast.domain.repositories

import com.pgustavo.openweather.currentWeather.domain.entities.Coordinates
import com.pgustavo.openweather.forecast.data.network.response.ForecastResponse

interface ForecastNetworkDataSource {
    suspend fun fetchForecast(coordinates: Coordinates): ForecastResponse?
}