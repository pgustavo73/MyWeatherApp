package com.pgustavo.openweather.currentWeather.presentation.ui

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pgustavo.openweather.R
import com.pgustavo.openweather.currentWeather.domain.entities.CurrentWeatherEntity

class WeatherAdapter(
    private var items: List<CurrentWeatherEntity>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onWeatherClickListener : ((data: CurrentWeatherEntity) -> Unit)? = null
    var onBtnDelClickListener : ((data: CurrentWeatherEntity) -> Unit)? = null
    var onAddButtonClickListener : (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.weather_item -> WeatherViewHolder.create(parent)
            R.layout.add_weather_item -> AddWeatherViewHolder.create(parent)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.weather_item -> {
                (holder as WeatherViewHolder).apply {
                    bind(items[position])
                    onClickListener = onWeatherClickListener
                    onDelClickListener = onBtnDelClickListener
                }
            }
            R.layout.add_weather_item -> {
                (holder as AddWeatherViewHolder).apply {
                    bind()
                    onClickListener = onAddButtonClickListener
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) {
            R.layout.add_weather_item
        } else {
            R.layout.weather_item
        }
    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

    fun submitList(items: List<CurrentWeatherEntity>) {
        this.items = items
        notifyDataSetChanged()
    }

    companion object {
        fun initListAdapter(
            recyclerView: RecyclerView,context: Context,
            items: List<CurrentWeatherEntity>): WeatherAdapter {
            return WeatherAdapter(items).apply {
                recyclerView.adapter = this
                recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,
                    false)
            }
        }
    }

}