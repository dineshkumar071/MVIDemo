package com.example.mvidemo.ui.weather

import com.example.mvidemo.repository.model.WCExample

sealed class WeatherState {
    object Idle:WeatherState()
    object Loading:WeatherState()
    data class WeatherDetail(val weather:WCExample):WeatherState()
    data class Error(val error:String):WeatherState()
}