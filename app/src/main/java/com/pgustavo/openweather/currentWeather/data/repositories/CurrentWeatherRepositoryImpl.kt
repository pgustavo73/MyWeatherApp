package com.pgustavo.openweather.currentWeather.data.repositories

import com.pgustavo.openweather.common.data.resouce.Resource
import com.pgustavo.openweather.common.util.Actions
import com.pgustavo.openweather.common.util.handleError
import com.pgustavo.openweather.common.util.mapToEntity
import com.pgustavo.openweather.common.util.mapToLocal
import com.pgustavo.openweather.currentWeather.data.local.CurrentWeatherData
import com.pgustavo.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.pgustavo.openweather.currentWeather.domain.repositories.CurrentWeatherLocalDataSource
import com.pgustavo.openweather.currentWeather.domain.repositories.CurrentWeatherRemoteDataSource
import com.pgustavo.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class CurrentWeatherRepositoryImpl(
    private val localDataSource: CurrentWeatherLocalDataSource,
    private val remoteDataSource: CurrentWeatherRemoteDataSource
) : CurrentWeatherRepository {

    private val defaultCities = listOf("New York", "Dublin", "Boston", "Gravata", "Recife")

    override suspend fun fetchNewWeather(city: String): Resource<Actions> {
        return try {
            fetchFromNetworkAndSave(city)
            Resource.success(Actions.AddNewWeather)
        } catch (e: Exception) {
            handleError(e, Actions.AddNewWeather)
        }
    }

    override suspend fun fetchWeatherDel(city: String): Resource<Actions> {
        return try {
            fetchFromNetworkAndDelete(city)
            Resource.success(Actions.AddNewWeather)
        } catch (e: Exception) {
            handleError(e, Actions.AddNewWeather)
        }
    }

    override fun observeAllWeather(): Flow<Resource<List<CurrentWeatherEntity>>> {
        return try {
            Resource.loading(null)
            localDataSource.observeAllWeather().map { weatherList ->
                Resource.success(mapLocalListToEntity(weatherList))
            }
        } catch (e: Exception) {
            flowOf(handleError(e))
        }
    }

    override suspend fun refreshCurrentWeatherList(): Resource<Actions> {
        return try {
            Resource.loading("")
            val cities: List<CurrentWeatherData> = localDataSource.getAllWeather()
            if (cities.isNotEmpty()) {
                cities.map {
                    fetchFromNetworkAndSave(it.city)
                }
            } else {
                defaultCities.map {
                    fetchFromNetworkAndSave(it)
                }
            }
            Resource.success(Actions.Refresh)
        } catch (e: Exception) {
            handleError(e, Actions.Refresh)
        }
    }

    override suspend fun initDefaultWeather(): Resource<Actions> {
        return try {
            fetchDefaultIfNeeded()
            Resource.success(Actions.InitDefaultWeather)
        } catch (e: Exception) {
            handleError(e, Actions.InitDefaultWeather)
        }
    }

    private suspend fun fetchDefaultIfNeeded() {
        val weatherList = localDataSource.getAllWeather()
        if (weatherList.isEmpty()) {
            defaultCities.forEach { city ->
                fetchFromNetworkAndSave(city)
            }
        }
    }

    private suspend fun fetchFromNetworkAndSave(city: String) {
        val remote = remoteDataSource.fetchCurrentWeather(city)
        val mapped = remote.mapToLocal()
        localDataSource.saveCurrentWeather(mapped)
    }

    private suspend fun fetchFromNetworkAndDelete(city: String) {
        val remote = remoteDataSource.fetchCurrentWeather(city)
        val mapped = remote.mapToLocal()
        localDataSource.deleteCurrentWeather(mapped)
    }

    private fun mapLocalListToEntity(local: List<CurrentWeatherData>): List<CurrentWeatherEntity> {
        return local.map {
            it.mapToEntity()
        }
    }
}