package com.example.mobileapp;

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val BASE_URL = "http://10.0.2.2:9000"
    private val API_KEY = "patoZZQTPZuUHlyhT.5ff0a6d6b070fd2d8412b974614cd2f5695adc57e971d212f61d8e3282126025"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request: Request = chain.request().newBuilder()
//                .addHeader("Authorization", "Bearer $API_KEY")
                .addHeader("Accept", "application/json")
                .build()
            Log.d("type d'appel","${request.url}")
            chain.proceed(request)
        }
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
