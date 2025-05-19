package com.example.weather_rever

import android.content.Context
import androidx.room.Room

object Graph {
    lateinit var database: WeatherDatabase

    val weatherRepository by lazy {
        RoomRepository(database.dao)
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, WeatherDatabase::class.java, "test.db").build()
    }
}