package com.example.mvidemo.di

import com.example.mvidemo.ui.weather.WeatherClimateActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeWeatherActivity(): WeatherClimateActivity
}