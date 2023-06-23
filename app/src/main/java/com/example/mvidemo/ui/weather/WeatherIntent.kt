package com.example.mvidemo.ui.weather

sealed class WeatherIntent {
    object FetchClimate : WeatherIntent()
}