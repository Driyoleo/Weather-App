package com.example.weather_rever

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val retrofit = Retrofit.Builder().baseUrl("https://api.weatherapi.com/v1/")
    .addConverterFactory(GsonConverterFactory.create()).build()

val apiService = retrofit.create(ApiService::class.java)


interface ApiService{

    @GET("current.json")
    suspend fun getCurrentWeather(@Query("key") key: String, @Query("q") q: String , @Query("aqi") aqi: String = "no") : WeatherData

}