package com.example.mvidemo.repository.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WCExample (
    @SerializedName("city")
    @Expose
    var city: WCCity? = null,

    @SerializedName("cod")
    @Expose
    var cod: String? = null,

    @SerializedName("message")
    @Expose
    var message: Double? = null,

    @SerializedName("cnt")
    @Expose
    var cnt: Int? = null,

    @SerializedName("list")
    @Expose
    var list: List<WCList?>? = null){
}