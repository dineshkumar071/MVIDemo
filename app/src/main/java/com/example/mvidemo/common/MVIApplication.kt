package com.example.mvidemo.common

import android.app.Application
import android.content.Context
import com.example.mvidemo.di.ApiModule
import com.example.mvidemo.di.AppComponent
import com.example.mvidemo.di.AppModule
import com.example.mvidemo.di.DaggerAppComponent
import com.example.mvidemo.repository.MVIPreference

class MVIApplication: Application() {
    private lateinit var mPreference: MVIPreference
    private lateinit var mAppComponent: AppComponent
    companion object {
        @JvmStatic  var instance: MVIApplication?=null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        myPreference(this)
        mAppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).apiModule(ApiModule("https://api.openweathermap.org/")).build()
    }

    public fun getComponent():AppComponent = mAppComponent

    /**creating the preference object*/
    private fun myPreference(context: Context)
    {
        mPreference = MVIPreference(context)
    }

    /**getter method for preference object*/
    fun getPrefer(): MVIPreference?{
        return mPreference
    }
}