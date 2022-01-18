package com.pgustavo.openweather.currentWeather.data.local

import androidx.room.*
import com.pgustavo.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDao {
    @Query("Select * from weather_table ORDER BY timestamp ASC")
    fun observeAllWeather(): Flow<List<CurrentWeatherData>>

    @Query("Select * from weather_table ORDER BY timestamp ASC")
    suspend fun getAllWeather(): List<CurrentWeatherData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: CurrentWeatherData)

    @Delete
    suspend fun delete(data: CurrentWeatherData) :Int
}