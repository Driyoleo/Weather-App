package com.example.weather_rever

import kotlinx.coroutines.flow.Flow

class RoomRepository (private val dao: WeatherDao) {

    suspend fun addWeatherData(weatherData: WeatherDataEntity) {
        dao.insertWeatherData(weatherData)
    }

    suspend fun updateWeatherData(weatherData: WeatherDataEntity) {
        dao.updateWeatherData(weatherData)
    }

    fun getWeatherData(): Flow<List<WeatherDataEntity>> {
        return dao.getWeatherData()
    }


}