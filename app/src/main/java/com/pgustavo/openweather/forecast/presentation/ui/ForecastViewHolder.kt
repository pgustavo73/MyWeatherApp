package com.pgustavo.openweather.forecast.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pgustavo.openweather.R
import com.pgustavo.openweather.databinding.ForecastItemBinding
import com.pgustavo.openweather.forecast.data.ForecastUI

class ForecastViewHolder(private val binding: ForecastItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ForecastUI) {
        binding.forecast = item
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): ForecastViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ForecastItemBinding>(
                inflater,
                R.layout.forecast_item,
                parent,
                false
            )
            return ForecastViewHolder(binding)
        }
    }

}