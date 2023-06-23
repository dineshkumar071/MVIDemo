package com.example.mvidemo.di

import com.example.mvidemo.repository.ApiCall
import com.example.mvidemo.repository.ApiCallImp
import com.example.mvidemo.service.MVIClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule(val baseUrl:String) {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(logger: Interceptor): OkHttpClient.Builder =
        OkHttpClient.Builder().addInterceptor(logger)

    @Provides
    @Singleton
    fun provideRetrofitInstance(gson: Gson, okHttp: OkHttpClient.Builder): MVIClient = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson)).client(okHttp.build())
        .build().create(MVIClient::class.java)

    @Provides
    @Singleton
    fun apiCall(client:MVIClient): ApiCall = ApiCallImp(client)
}