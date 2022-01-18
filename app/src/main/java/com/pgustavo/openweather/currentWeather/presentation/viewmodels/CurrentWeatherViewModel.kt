package com.pgustavo.openweather.currentWeather.presentation.viewmodels

import androidx.lifecycle.*
import com.pgustavo.openweather.common.data.resouce.Resource
import com.pgustavo.openweather.common.util.Actions
import com.pgustavo.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.pgustavo.openweather.currentWeather.domain.usecases.*
import kotlinx.coroutines.launch

class CurrentWeatherViewModel(
    private val fetchWeatherListUseCase: FetchWeatherListUseCase,
    private val addNewWeatherUseCase: FetchNewWeatherUseCase,
    private val refreshWeatherListUseCase: RefreshWeatherListUseCase,
    private val initDefaultWeatherUseCase: InitDefaultWeatherUseCase,
    private val deleteWeatherUseCase: FetchWeatherDelUseCase

) : ViewModel() {

    private val _status = MutableLiveData<Resource<Actions>>()
    val status: LiveData<Resource<Actions>> = _status

    fun fetchWeather(): LiveData<Resource<List<CurrentWeatherEntity>>> {
        return fetchWeatherListUseCase.fetchAllWeather().asLiveData()
    }

    fun addNewWeather(city: String) {
        viewModelScope.launch {
            _status.value = Resource.loading(Actions.AddNewWeather)
            val data = addNewWeatherUseCase.fetchCurrentWeather(city)
            _status.value = data
        }
    }

    fun deleteWeather(city: String) {
        viewModelScope.launch {
            _status.value = Resource.loading(Actions.AddNewWeather)
            val data = deleteWeatherUseCase.fetchDelWeather(city)
            _status.value = data
        }
    }

    fun refreshWeatherList() {
        viewModelScope.launch {
            _status.value = Resource.loading(Actions.Refresh)
            _status.value = refreshWeatherListUseCase.refreshCurrentWeatherList()
        }
    }

    fun initDefaultWeather() {
        viewModelScope.launch {
            _status.value = Resource.loading(Actions.InitDefaultWeather)
            _status.value = initDefaultWeatherUseCase.initDefaultWeather()
        }
    }

    fun clearStatus() {
        _status.value = Resource.loading(Actions.Empty)
    }

}