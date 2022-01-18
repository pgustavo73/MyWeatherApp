package com.pgustavo.openweather.common.util

import com.pgustavo.openweather.currentWeather.data.local.CurrentWeatherData
import com.pgustavo.openweather.currentWeather.data.network.response.CurrentWeatherResponse
import com.pgustavo.openweather.currentWeather.data.ui.CoordinatesUI
import com.pgustavo.openweather.currentWeather.data.ui.CurrentWeatherUI
import com.pgustavo.openweather.currentWeather.domain.entities.Coordinates
import com.pgustavo.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.pgustavo.openweather.forecast.data.ForecastUI
import com.pgustavo.openweather.forecast.data.network.response.Current
import com.pgustavo.openweather.forecast.data.network.response.ForecastResponse
import com.pgustavo.openweather.forecast.domain.entities.Forecast
import com.pgustavo.openweather.forecast.domain.entities.WeatherDetailsEntity

fun CurrentWeatherEntity.mapToUI(): CurrentWeatherUI = CurrentWeatherUI(
    CoordinatesUI(this.coordinates.lat, this.coordinates.lon),
    this.city,
    this.condition,
    this.iconUrl,
    this.temperature
)

fun CurrentWeatherResponse.mapToLocal(): CurrentWeatherData = CurrentWeatherData(
    this.weatherId,
    this.cityName,
    this.coordinates.lat,
    this.coordinates.lon,
    this.weather[0].description,
    this.weather[0].icon,
    this.main.temp
)

fun CurrentWeatherData.mapToEntity(): CurrentWeatherEntity = CurrentWeatherEntity(
    Coordinates(this.latitude, this.longitude),
    this.city,
    this.condition,
    this.iconUrl,
    this.temperature.toInt()
)

fun ForecastResponse.mapToEntity(): WeatherDetailsEntity {
    return WeatherDetailsEntity(
        this.current.pressure,
        this.current.humidity,
        this.current.windSpeed,
        this.daily.map {
            try {
                Forecast(
                    it.dt.toLong(),
                    it.weather[0].main,
                    it.weather[0].icon,
                    it.temp.day.toInt()
                )
            } catch (e: Exception) {
                Forecast(
                    it.dt.toLong(),
                    "",
                    "",
                    0
                )
            }
        })
}

fun List<Forecast>.mapToUI(): List<ForecastUI> {
    return this.map {
        ForecastUI(
            it.condition,
            it.iconUrl,
            it.temperature
        )
    }
}

