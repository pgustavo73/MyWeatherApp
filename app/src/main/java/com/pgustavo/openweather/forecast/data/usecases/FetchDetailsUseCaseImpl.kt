package com.pgustavo.openweather.forecast.data.usecases

import com.pgustavo.openweather.common.data.resouce.Resource
import com.pgustavo.openweather.currentWeather.domain.entities.Coordinates
import com.pgustavo.openweather.forecast.domain.entities.WeatherDetailsEntity
import com.pgustavo.openweather.forecast.domain.repositories.ForecastRepository
import com.pgustavo.openweather.forecast.domain.usecases.FetchDetailsUseCase

class FetchDetailsUseCaseImpl(
    private val repository: ForecastRepository
) : FetchDetailsUseCase {
    override suspend fun fetchForecast(coordinates: Coordinates): Resource<WeatherDetailsEntity> {
        return repository.fetchForecast(coordinates)
    }
}