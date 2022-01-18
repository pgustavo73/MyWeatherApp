package com.pgustavo.openweather.forecast.data.repositories

import com.pgustavo.openweather.common.data.resouce.Resource
import com.pgustavo.openweather.common.util.handleError
import com.pgustavo.openweather.common.util.mapToEntity
import com.pgustavo.openweather.currentWeather.domain.entities.Coordinates
import com.pgustavo.openweather.forecast.domain.entities.WeatherDetailsEntity
import com.pgustavo.openweather.forecast.domain.repositories.ForecastNetworkDataSource
import com.pgustavo.openweather.forecast.domain.repositories.ForecastRepository

class ForecastRepositoryImpl(
    private val dataSource: ForecastNetworkDataSource
) : ForecastRepository {
    override suspend fun fetchForecast(coordinates: Coordinates): Resource<WeatherDetailsEntity> {
        return try {
            val data = dataSource.fetchForecast(coordinates)?.mapToEntity()
            if (data != null) Resource.success(data) else Resource.error("", null)
        } catch (e: Exception) {
            handleError(e)
        }
    }
}