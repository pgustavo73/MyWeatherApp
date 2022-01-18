package com.pgustavo.openweather.currentWeather.data.network.response


import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("description")
    var description: String = "",
    @SerializedName("icon")
    var icon: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("main")
    var main: String = ""
)