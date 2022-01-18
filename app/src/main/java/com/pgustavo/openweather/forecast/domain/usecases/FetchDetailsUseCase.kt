package com.pgustavo.openweather.forecast.domain.usecases

import com.pgustavo.openweather.common.data.resouce.Resource
import com.pgustavo.openweather.currentWeather.domain.entities.Coordinates
import com.pgustavo.openweather.forecast.domain.entities.WeatherDetailsEntity

interface FetchDetailsUseCase {
    suspend fun fetchForecast(coordinates: Coordinates): Resource<WeatherDetailsEntity>
}