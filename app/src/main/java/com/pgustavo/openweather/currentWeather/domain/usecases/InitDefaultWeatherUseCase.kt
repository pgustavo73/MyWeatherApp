package com.pgustavo.openweather.currentWeather.domain.usecases

import com.pgustavo.openweather.common.util.Actions
import com.pgustavo.openweather.common.data.resouce.Resource

interface InitDefaultWeatherUseCase {
    suspend fun initDefaultWeather(): Resource<Actions>
}