package com.example.weather_rever

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [WeatherDataEntity::class] ,
    version = 2,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val dao: WeatherDao
}
