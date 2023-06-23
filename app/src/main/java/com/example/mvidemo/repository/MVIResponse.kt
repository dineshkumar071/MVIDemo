package com.example.mvidemo.repository

import com.example.mvidemo.utilities.Status

data class MVIResponse<out T>(val status: Status?, val data:T?, val message:String?) {
    companion object{

        fun<T> success(data:T?):MVIResponse<T>{
            return MVIResponse(Status.SUCCESS, data, "")
        }

        fun <T> error(msg: String): MVIResponse<T> {
            return MVIResponse(Status.ERROR, null, msg)
        }

        fun <T> loading(data: T?): MVIResponse<T> {
            return MVIResponse(Status.LOADING, data, null)
        }
    }
}