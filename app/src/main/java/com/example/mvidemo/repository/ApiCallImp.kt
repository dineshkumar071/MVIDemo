package com.example.mvidemo.repository

import com.example.mvidemo.repository.model.WCExample
import com.example.mvidemo.service.MVIClient
import retrofit2.Call
import javax.inject.Inject

class ApiCallImp @Inject constructor(private val client: MVIClient) : ApiCall {

    override suspend fun getClimate(): Call<WCExample> = client.getClimate()

}