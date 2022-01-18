package com.pgustavo.openweather.currentWeather.domain.repositories

import com.pgustavo.openweather.common.util.Actions
import com.pgustavo.openweather.common.data.resouce.Resource
import com.pgustavo.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {
    suspend fun initDefaultWeather() : Resource<Actions>
    suspend fun fetchNewWeather(city: String): Resource<Actions>
    suspend fun fetchWeatherDel(city: String): Resource<Actions>
    fun observeAllWeather(): Flow<Resource<List<CurrentWeatherEntity>>>
    suspend fun refreshCurrentWeatherList(): Resource<Actions>
}