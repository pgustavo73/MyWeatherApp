package com.pgustavo.openweather.currentWeather.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pgustavo.openweather.R
import com.pgustavo.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.pgustavo.openweather.databinding.WeatherItemBinding

class WeatherViewHolder(
    private val binding: WeatherItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    var onClickListener: ((data: CurrentWeatherEntity) -> Unit)? = null
    var onDelClickListener : ((data: CurrentWeatherEntity) -> Unit)? = null

    fun bind(item: CurrentWeatherEntity) {
        binding.weather = item
        binding.bntDelete.setOnClickListener { onDelClickListener?.let { it1 -> it1(item) } }
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            onClickListener?.invoke(item)
        }
    }

    companion object {
        fun create(parent: ViewGroup): WeatherViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<WeatherItemBinding>(
                inflater,
                R.layout.weather_item,
                parent,
                false
            )
            return WeatherViewHolder(binding)
        }
    }
}