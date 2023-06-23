package com.example.mvidemo.repository

import com.example.mvidemo.common.MVIApplication.Companion.instance
import com.example.mvidemo.repository.model.WCExample
import com.google.gson.GsonBuilder

object MVIRepository {


    fun saveInSharedPreference(climate: WCExample?) {
        val userGson = GsonBuilder().create()
        val city: String? = userGson.toJson(climate)
        instance?.getPrefer()?.climate = city
    }

    fun retrieveFromSharedPreference(): String? = instance?.getPrefer()?.climate
}