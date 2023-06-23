package com.example.mvidemo.ui.weather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvidemo.repository.ApiCall
import com.example.mvidemo.repository.MVIRepository
import com.example.mvidemo.repository.model.WCExample
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class WeatherClimateViewModel @Inject constructor(mApplication: Application, val apiCall: ApiCall) : AndroidViewModel(mApplication){

    val weatherIntent = Channel<WeatherIntent> (Channel.UNLIMITED)
    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Idle)
    val weatherState :StateFlow<WeatherState> get() = _weatherState


    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            weatherIntent.consumeAsFlow().collect{
                when(it){
                    is WeatherIntent.FetchClimate -> retrieveClimate()
                }
            }
        }
    }

    suspend fun retrieveClimate(){
        _weatherState.value = WeatherState.Loading
        val climate = apiCall.getClimate()
        climate.enqueue(object : Callback<WCExample> {
            override fun onResponse(call: Call<WCExample>, response: Response<WCExample>) {
                if(response.isSuccessful){
                    if(response.body() != null) {
                        _weatherState.value = WeatherState.WeatherDetail(response.body()!!)
                    }else{
                        _weatherState.value = WeatherState.Error("Response data was null")
                    }
                    MVIRepository.saveInSharedPreference(response.body())
                }else{
                    _weatherState.value = WeatherState.Error("response correct no data")
                }
            }

            override fun onFailure(call: Call<WCExample>, t: Throwable) {
                _weatherState.value = WeatherState.Error("response fail")
            }
        })
    }

    /*  https://api.openweathermap.org/data/2.5/forecast/daily?q=London&mode=xml&units=metric&cnt=7&appid=&appid=82a3c576ed39bfde1c054ddb73f4cec0
      api.openweathermap.org/data/2.5/forecast/daily?q=london&cnt=7&appid=82a3c576ed39bfde1c054ddb73f4cec0
  */
}