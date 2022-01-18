package com.pgustavo.openweather.currentWeather.data.usecases

import com.pgustavo.openweather.common.data.resouce.Resource
import com.pgustavo.openweather.common.util.Actions
import com.pgustavo.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import com.pgustavo.openweather.currentWeather.domain.usecases.FetchWeatherDelUseCase

class FetchWeatherDelUseCaseImpl (
    private val repository: CurrentWeatherRepository
) : FetchWeatherDelUseCase {
    override suspend fun fetchDelWeather(city: String): Resource<Actions> {
        return repository.fetchWeatherDel(city)
    }
}
