package com.example.mvidemo.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.mvidemo.ViewModelFactory
import com.example.mvidemo.repository.ApiCall
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {

    @Provides
    @Singleton
    fun viewModelFactory(mApplication: Application, apiCall: ApiCall): ViewModelProvider.Factory{
        return  ViewModelFactory(mApplication,apiCall)
    }
}