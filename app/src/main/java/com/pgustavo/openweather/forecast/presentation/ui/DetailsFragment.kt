package com.pgustavo.openweather.forecast.presentation.ui


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.pgustavo.openweather.R
import com.pgustavo.openweather.common.data.resouce.Resource
import com.pgustavo.openweather.common.presentation.ui.BindingFragment
import com.pgustavo.openweather.common.util.mapToUI
import com.pgustavo.openweather.currentWeather.data.ui.CurrentWeatherUI
import com.pgustavo.openweather.currentWeather.domain.entities.Coordinates
import com.pgustavo.openweather.databinding.FragmentDetailsBinding
import com.pgustavo.openweather.forecast.data.ConditionData
import com.pgustavo.openweather.forecast.domain.entities.WeatherDetailsEntity
import com.pgustavo.openweather.forecast.presentation.viewmodels.DetailsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsFragment : BindingFragment(R.layout.fragment_details) {

    private lateinit var detailsBinding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModel()
    private lateinit var adapter: ForecastAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsBinding = (binding as FragmentDetailsBinding)
        adapter = ForecastAdapter.initLinearAdapter(detailsBinding.forecastRv, this.requireContext(), listOf())
    }

    override fun onStart() {
        super.onStart()
        getCurrentWeatherData()?.let {
            viewModel.fetchWeatherDetails(
                Coordinates(it.coordinates.lat, it.coordinates.lon)
            )
            detailsBinding.currentWeather = it
        }
        viewModel.weatherDetails.observe(this, Observer {
            when (it.status) {
                Resource.Status.LOADING -> showLoading()
                Resource.Status.SUCCESS -> showSuccess(it.data ?: WeatherDetailsEntity(0, 0, 0.00, listOf()))
                Resource.Status.ERROR, Resource.Status.NETWORKERROR -> showError()
            }
        })
    }

    private fun showLoading() {
        showProgressBar(true)
    }

    private fun showSuccess(data: WeatherDetailsEntity) {
        bindView(data)
        adapter.submitItems(data.dailyForecast.mapToUI())
        showErrorLayout(false)
        showProgressBar(false)
    }

    private fun showError() {
        showProgressBar(false)
        showErrorLayout(true)
    }

    private fun getCurrentWeatherData(): CurrentWeatherUI? {
        return try {
            args.currentWeather
        } catch (e: IllegalStateException) {
            null
        }
    }

    private fun showProgressBar(show: Boolean) {
        detailsBinding.loading.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showErrorLayout(show: Boolean) {
        detailsBinding.error.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun bindView(data: WeatherDetailsEntity) {
        detailsBinding.details = data
        detailsBinding.condition = ConditionData(getString(R.string.wind), resources.getDrawable(R.drawable.wind))
        detailsBinding.pressure =
            ConditionData(getString(R.string.pressure), resources.getDrawable(R.drawable.pressure))
        detailsBinding.humidity =
            ConditionData(getString(R.string.humidity), resources.getDrawable(R.drawable.humidity))
        detailsBinding.executePendingBindings()
    }
}
