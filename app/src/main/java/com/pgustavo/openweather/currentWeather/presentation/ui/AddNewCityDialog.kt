package com.pgustavo.openweather.currentWeather.presentation.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.pgustavo.openweather.R
import com.pgustavo.openweather.currentWeather.presentation.viewmodels.CurrentWeatherViewModel
import kotlinx.android.synthetic.main.add_new_city_dialog.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AddNewCityDialog : DialogFragment() {

    private val viewModel: CurrentWeatherViewModel by sharedViewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.add_new_city_dialog, null)
            builder.setView(view)
                .setTitle("")
                .setNegativeButton(
                    getString(R.string.cancel)
                ) { _, _ -> }
                .setPositiveButton(
                    getString(R.string.add)
                ) { _, _ ->
                    val city = view.city.text.toString().trim().toLowerCase()
                    if (city.isNotEmpty())
                        viewModel.addNewWeather(city)
                }
            return builder.create()
        }
        return super.onCreateDialog(savedInstanceState)
    }

}