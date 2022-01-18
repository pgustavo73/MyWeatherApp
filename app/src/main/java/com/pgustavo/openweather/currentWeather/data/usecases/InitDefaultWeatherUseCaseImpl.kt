package com.pgustavo.openweather.currentWeather.data.usecases

import com.pgustavo.openweather.common.util.Actions
import com.pgustavo.openweather.common.data.resouce.Resource
import com.pgustavo.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import com.pgustavo.openweather.currentWeather.domain.usecases.InitDefaultWeatherUseCase

class InitDefaultWeatherUseCaseImpl(private val repository: CurrentWeatherRepository) :
    InitDefaultWeatherUseCase {
    override suspend fun initDefaultWeather(): Resource<Actions> {
        return repository.initDefaultWeather()
    }
}