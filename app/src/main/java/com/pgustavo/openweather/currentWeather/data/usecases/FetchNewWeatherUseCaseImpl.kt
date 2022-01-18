package com.pgustavo.openweather.currentWeather.data.usecases

import com.pgustavo.openweather.common.util.Actions
import com.pgustavo.openweather.common.data.resouce.Resource
import com.pgustavo.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import com.pgustavo.openweather.currentWeather.domain.usecases.FetchNewWeatherUseCase

class FetchNewWeatherUseCaseImpl(
    private val repository: CurrentWeatherRepository
) : FetchNewWeatherUseCase {
    override suspend fun fetchCurrentWeather(city: String): Resource<Actions> {
        return repository.fetchNewWeather(city)
    }
}