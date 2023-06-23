package com.example.mvidemo.repository.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WCList {
    @SerializedName("dt")
    @Expose
    var dt: Int? = null

    @SerializedName("sunrise")
    @Expose
    var sunrise: Int? = null

    @SerializedName("sunset")
    @Expose
    var sunset: Int? = null

    @SerializedName("temp")
    @Expose
    var temp: WCTemp? = null

    @SerializedName("feels_like")
    @Expose
    var feelsLike: WCFeelsLike? = null

    @SerializedName("pressure")
    @Expose
    var pressure: Int? = null

    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null

    @SerializedName("weather")
    @Expose
    var weather: List<WCWeather?>? = null

    @SerializedName("speed")
    @Expose
    var speed: Double? = null

    @SerializedName("deg")
    @Expose
    var deg: Int? = null

    @SerializedName("clouds")
    @Expose
    var clouds: Int? = null

    @SerializedName("pop")
    @Expose
    var pop: Double? = null

    @SerializedName("snow")
    @Expose
    var snow: Double? = null

    @SerializedName("rain")
    @Expose
    var rain: Double? = null
}