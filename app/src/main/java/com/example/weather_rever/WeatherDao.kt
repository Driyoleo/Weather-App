package com.example.weather_rever

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WeatherDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertWeatherData(weatherData: WeatherDataEntity)

    @Update
    abstract suspend fun updateWeatherData(weatherData: WeatherDataEntity)

    @Query("SELECT * FROM weather_data")
    abstract fun getWeatherData() : Flow<List<WeatherDataEntity>>

    // query to clear all data from databse
    @Query("DELETE FROM weather_data")
    abstract suspend fun clearWeatherData()

    @Query("SELECT COUNT(*) FROM weather_data")
    abstract fun getCount() : Flow<Int>

}