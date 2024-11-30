package com.example.demoproduct

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("products") // getting the api through its end point
    fun getproductdata(): Call<MyData> // calling the main data class
}