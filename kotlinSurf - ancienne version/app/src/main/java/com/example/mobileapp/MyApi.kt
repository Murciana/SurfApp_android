package com.example.mobileapp

import SpotRecord
import retrofit2.Call
import retrofit2.http.GET

interface MyApi {
    @GET("/spot/get-all")
    fun getRecords() : Call<SpotRecord>
}