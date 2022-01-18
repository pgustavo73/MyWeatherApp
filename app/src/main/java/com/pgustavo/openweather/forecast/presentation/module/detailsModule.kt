package com.pgustavo.openweather.forecast.presentation.module

import com.pgustavo.openweather.forecast.data.network.ForecastApi
import com.pgustavo.openweather.forecast.data.repositories.ForecastNetworkDataSourceImpl
import com.pgustavo.openweather.forecast.data.repositories.ForecastRepositoryImpl
import com.pgustavo.openweather.forecast.data.usecases.FetchDetailsUseCaseImpl
import com.pgustavo.openweather.forecast.domain.repositories.ForecastNetworkDataSource
import com.pgustavo.openweather.forecast.domain.repositories.ForecastRepository
import com.pgustavo.openweather.forecast.domain.usecases.FetchDetailsUseCase
import com.pgustavo.openweather.forecast.presentation.viewmodels.DetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val detailsModule = module {
    single<ForecastApi> { (get() as Retrofit).create(ForecastApi::class.java) }
    single<ForecastNetworkDataSource> { ForecastNetworkDataSourceImpl(api = get()) }
    single<ForecastRepository> {
        ForecastRepositoryImpl(
            dataSource = get()
        )
    }
    single<FetchDetailsUseCase> { FetchDetailsUseCaseImpl(repository = get()) }
    viewModel { DetailsViewModel(fetchDetailsUseCase = get()) }
}