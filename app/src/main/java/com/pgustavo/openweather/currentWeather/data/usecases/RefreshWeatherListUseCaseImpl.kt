package com.pgustavo.openweather.currentWeather.data.usecases

import com.pgustavo.openweather.common.util.Actions
import com.pgustavo.openweather.common.data.resouce.Resource
import com.pgustavo.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import com.pgustavo.openweather.currentWeather.domain.usecases.RefreshWeatherListUseCase

class RefreshWeatherListUseCaseImpl(
    private val repository: CurrentWeatherRepository
) : RefreshWeatherListUseCase {

    override suspend fun refreshCurrentWeatherList(): Resource<Actions> {
        return repository.refreshCurrentWeatherList()
    }
}