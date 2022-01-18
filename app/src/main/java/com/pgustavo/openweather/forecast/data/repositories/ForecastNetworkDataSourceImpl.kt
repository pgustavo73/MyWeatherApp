package com.pgustavo.openweather.forecast.data.repositories

import com.pgustavo.openweather.currentWeather.domain.entities.Coordinates
import com.pgustavo.openweather.forecast.data.network.ForecastApi
import com.pgustavo.openweather.forecast.data.network.response.ForecastResponse
import com.pgustavo.openweather.forecast.domain.repositories.ForecastNetworkDataSource

class ForecastNetworkDataSourceImpl(
    private val api: ForecastApi
) : ForecastNetworkDataSource {
    override suspend fun fetchForecast(coordinates: Coordinates): ForecastResponse {
        return api.fetchForecast(coordinates.lat, coordinates.lon)
    }
}