package com.example.mvidemo.di

import com.example.mvidemo.ui.weather.WeatherClimateActivity
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,AppModule::class,ApiModule::class,ViewModelFactoryModule::class,ActivityModule::class])
interface AppComponent {

    fun inject(activity: WeatherClimateActivity)
}