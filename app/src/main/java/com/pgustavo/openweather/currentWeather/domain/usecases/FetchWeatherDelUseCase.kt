package com.pgustavo.openweather.currentWeather.domain.usecases

import com.pgustavo.openweather.common.data.resouce.Resource
import com.pgustavo.openweather.common.util.Actions


interface FetchWeatherDelUseCase {
      suspend fun fetchDelWeather(city: String): Resource<Actions>
    }
