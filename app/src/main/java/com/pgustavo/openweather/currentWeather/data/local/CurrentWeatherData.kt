package com.pgustavo.openweather.currentWeather.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class CurrentWeatherData(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "weather_id")
    var weatherId: Int,

    @ColumnInfo(name = "city_name")
    var city: String,

    @ColumnInfo(name = "latitude")
    var latitude: Double,

    @ColumnInfo(name = "longitude")
    var longitude: Double,

    @ColumnInfo(name = "condition")
    var condition: String,

    @ColumnInfo(name = "icon_url")
    var iconUrl: String = "",

    @ColumnInfo(name = "temp")
    var temperature: Double = 0.00,

    @ColumnInfo(name = "timestamp")
    var timestamp: Long = System.currentTimeMillis()
)