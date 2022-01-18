package com.pgustavo.openweather.currentWeather.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.pgustavo.openweather.R
import com.pgustavo.openweather.common.data.resouce.Resource
import com.pgustavo.openweather.common.presentation.ui.BindingFragment
import com.pgustavo.openweather.common.util.Actions
import com.pgustavo.openweather.common.util.mapToUI
import com.pgustavo.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.pgustavo.openweather.currentWeather.presentation.viewmodels.CurrentWeatherViewModel
import com.pgustavo.openweather.databinding.FragmentCurrentWeatherBinding
import kotlinx.android.synthetic.main.fragment_current_weather.*
import kotlinx.android.synthetic.main.fragment_current_weather.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel



class CurrentWeatherFragment : BindingFragment(R.layout.fragment_current_weather) {

    private lateinit var currentWeatherBinding: FragmentCurrentWeatherBinding
    private val viewModel: CurrentWeatherViewModel by sharedViewModel()
    private lateinit var adapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initDefaultWeather()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentWeatherBinding = (binding as FragmentCurrentWeatherBinding)
        adapter = WeatherAdapter.initListAdapter(currentWeatherBinding.weatherList,
            this.requireContext(),listOf())
        swipeToRefreshListener()
    }

    override fun onStart() {
        super.onStart()
        observeWeatherList()
        setupOnWeatherClickListeners()
        displayActionStatus()
    }

    override fun onStop() {
        super.onStop()
        viewModel.clearStatus()
    }

    private fun observeWeatherList() {
        viewModel.fetchWeather().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.LOADING -> showProgressBar()
                Resource.Status.SUCCESS -> {
                    adapter.submitList(it.data ?: listOf())
                }
                Resource.Status.ERROR, Resource.Status.NETWORKERROR -> {
                    hideProgressBar()
                    showErrorMessage()
                }
            }
        })
    }

    private fun setupOnWeatherClickListeners() {
        adapter.apply {
            onAddButtonClickListener = {
                openDialog()
            }
            onWeatherClickListener = {
                navigateToDetails(it)
            }
            onBtnDelClickListener = {
                removeWeather(it)
            }
        }
    }

    private fun removeWeather(it: CurrentWeatherEntity) {
        Toast.makeText(context, "Removing ${it.city}", Toast.LENGTH_SHORT).show()
        viewModel.deleteWeather(it.city)
    }

    private fun navigateToDetails(it: CurrentWeatherEntity) {
        val data = it.mapToUI()
        val action = CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToDetailsFragment(data)
        findNavController().navigate(action)
    }

    private fun openDialog() {
        findNavController().navigate(R.id.action_currentWeatherFragment_to_addNewCityDialog)
    }

    private fun swipeToRefreshListener() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshWeatherList()
        }
    }

    private fun displayActionStatus() {
        viewModel.status.observe(viewLifecycleOwner, Observer {
            when (it.data) {
                Actions.Refresh -> showRefreshStatus(it.status)
                Actions.InitDefaultWeather -> showInitWeatherStatus(it.status)
                Actions.AddNewWeather -> showAddNewWeatherStatus(it.status)
            }
        })
    }

    private fun showRefreshStatus(status: Resource.Status?) {
        when (status) {
            Resource.Status.LOADING -> {
                hideInitError()
                hideRecyclerView(true)
            }
            Resource.Status.SUCCESS -> {
                hideInitError()
                hideRecyclerView(false)
                swipeRefreshLayout.isRefreshing = false
                showToast(R.string.weather_success_refresh)
            }
            Resource.Status.ERROR, Resource.Status.NETWORKERROR -> {
                swipeRefreshLayout.isRefreshing = false
                showToast(R.string.weather_failed_refresh)
            }
        }
    }

    private fun showInitWeatherStatus(status: Resource.Status?) {
        when (status) {
            Resource.Status.LOADING -> {
                showProgressBar()
                hideRecyclerView(true)
            }
            Resource.Status.SUCCESS -> {
                hideProgressBar()
                hideRecyclerView(false)
            }
            Resource.Status.ERROR, Resource.Status.NETWORKERROR -> {
                hideProgressBar()
                showInitError()
            }
        }
    }

    private fun showAddNewWeatherStatus(status: Resource.Status?) {
        when (status) {
            Resource.Status.LOADING -> showProgressBar()
            Resource.Status.SUCCESS -> {
                hideProgressBar()
            }
            Resource.Status.ERROR, Resource.Status.NETWORKERROR -> {
                hideProgressBar()
                showErrorMessage()
            }
        }
    }

    private fun showProgressBar() {
        currentWeatherBinding.loadingLayout.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        currentWeatherBinding.loadingLayout.visibility = View.GONE
    }

    private fun showErrorMessage() {
        Snackbar.make(this.requireView(), getString(R.string.read_weather_fail), Snackbar.LENGTH_SHORT).show()
    }

    private fun showInitError() {
        currentWeatherBinding.errorLayout.visibility = View.VISIBLE
    }

    private fun hideInitError() {
        currentWeatherBinding.errorLayout.visibility = View.GONE
    }

    private fun hideRecyclerView(hide: Boolean) {
        currentWeatherBinding.swipeRefreshLayout.weatherList.visibility = if (hide) View.GONE else View.VISIBLE
    }

    private fun showToast(message: Int) {
        Toast.makeText(
            this.requireContext(),
            getString(message),
            Toast.LENGTH_SHORT
        ).show()
    }
}
