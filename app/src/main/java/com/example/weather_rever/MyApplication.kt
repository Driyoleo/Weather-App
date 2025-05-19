package com.example.weather_rever

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}