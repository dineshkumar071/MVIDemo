package com.example.mvidemo.repository.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WCCoord {
    @SerializedName("lon")
    @Expose
    var lon: Double? = null

    @SerializedName("lat")
    @Expose
    var lat: Double? = null
}