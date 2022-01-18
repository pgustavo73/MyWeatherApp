package com.pgustavo.openweather.currentWeather.domain.usecases

import com.pgustavo.openweather.common.data.resouce.Resource
import com.pgustavo.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow

interface FetchWeatherListUseCase {
    fun fetchAllWeather(): Flow<Resource<List<CurrentWeatherEntity>>>
}