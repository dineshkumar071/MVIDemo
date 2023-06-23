package com.example.mvidemo.repository

import com.example.mvidemo.repository.model.WCExample
import retrofit2.Call

interface ApiCall {

    suspend fun getClimate(): Call<WCExample>

}