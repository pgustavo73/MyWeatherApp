package com.pgustavo.openweather.common.presentation.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load

@BindingAdapter("loadImage")
fun bindImage(imageView: ImageView, url: String) {
    imageView.load("https://openweathermap.org/img/wn/${url}@2x.png") {
        crossfade(true)
    }
}
