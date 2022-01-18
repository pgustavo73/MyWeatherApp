package com.pgustavo.openweather.currentWeather.presentation.module

import com.pgustavo.openweather.common.data.local.WeatherDatabase
import com.pgustavo.openweather.currentWeather.data.local.CurrentWeatherLocalDataSourceImpl
import com.pgustavo.openweather.currentWeather.data.network.CurrentWeatherApi
import com.pgustavo.openweather.currentWeather.data.network.CurrentWeatherRemoteDataSourceImpl
import com.pgustavo.openweather.currentWeather.data.repositories.CurrentWeatherRepositoryImpl
import com.pgustavo.openweather.currentWeather.data.usecases.*
import com.pgustavo.openweather.currentWeather.domain.repositories.CurrentWeatherLocalDataSource
import com.pgustavo.openweather.currentWeather.domain.repositories.CurrentWeatherRemoteDataSource
import com.pgustavo.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import com.pgustavo.openweather.currentWeather.domain.usecases.*
import com.pgustavo.openweather.currentWeather.presentation.viewmodels.CurrentWeatherViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val currentWeatherModule = module {
    single { (get() as WeatherDatabase).currentWeatherDao() }
    single<CurrentWeatherApi> { (get() as Retrofit).create(CurrentWeatherApi::class.java) }
    single<CurrentWeatherLocalDataSource> { CurrentWeatherLocalDataSourceImpl(dao = get()) }
    single<CurrentWeatherRemoteDataSource> { CurrentWeatherRemoteDataSourceImpl(weatherApi = get()) }
    single<CurrentWeatherRepository> { CurrentWeatherRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }
    single<FetchWeatherListUseCase> { FetchWeatherListUseCaseImpl(repository = get()) }
    single<FetchNewWeatherUseCase> { FetchNewWeatherUseCaseImpl(repository = get()) }
    single<FetchWeatherDelUseCase> { FetchWeatherDelUseCaseImpl(repository = get()) }
    single<RefreshWeatherListUseCase> { RefreshWeatherListUseCaseImpl(repository = get()) }
    single<InitDefaultWeatherUseCase> { InitDefaultWeatherUseCaseImpl(repository = get()) }
    viewModel {
        CurrentWeatherViewModel(
            fetchWeatherListUseCase = get(),
            addNewWeatherUseCase = get(),
            refreshWeatherListUseCase = get(),
            initDefaultWeatherUseCase = get(),
            deleteWeatherUseCase = get()
        )
    }
}