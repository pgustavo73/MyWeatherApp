package com.pgustavo.openweather.currentWeather.data.usecases

import com.pgustavo.openweather.common.data.resouce.Resource
import com.pgustavo.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.pgustavo.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import com.pgustavo.openweather.currentWeather.domain.usecases.FetchWeatherListUseCase
import kotlinx.coroutines.flow.Flow

class FetchWeatherListUseCaseImpl(
    private val repository: CurrentWeatherRepository
) : FetchWeatherListUseCase {

    override fun fetchAllWeather(): Flow<Resource<List<CurrentWeatherEntity>>> {
        return repository.observeAllWeather()
    }
}