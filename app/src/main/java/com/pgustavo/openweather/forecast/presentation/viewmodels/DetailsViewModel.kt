package com.pgustavo.openweather.forecast.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pgustavo.openweather.common.data.resouce.Resource
import com.pgustavo.openweather.currentWeather.domain.entities.Coordinates
import com.pgustavo.openweather.forecast.domain.entities.WeatherDetailsEntity
import com.pgustavo.openweather.forecast.domain.usecases.FetchDetailsUseCase
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val fetchDetailsUseCase: FetchDetailsUseCase
) : ViewModel() {

    private val _details = MutableLiveData<Resource<WeatherDetailsEntity>>()
    val weatherDetails : LiveData<Resource<WeatherDetailsEntity>> = _details

    fun fetchWeatherDetails(coordinates: Coordinates){
        viewModelScope.launch {
            _details.value = Resource.loading(null)
            _details.value = fetchDetailsUseCase.fetchForecast(coordinates)
        }
    }
}