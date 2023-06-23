package com.example.mvidemo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvidemo.repository.ApiCall
import com.example.mvidemo.ui.weather.WeatherClimateViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(val mApplication: Application, val creator: ApiCall) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass == WeatherClimateViewModel::class.java) {
            return WeatherClimateViewModel(mApplication, creator) as T
        }else{
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}