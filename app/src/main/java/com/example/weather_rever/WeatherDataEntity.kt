package com.example.weather_rever


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_data")
data class WeatherDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0, // It's good practice to have a primary key

    @Embedded(prefix = "location_") // Prefix to avoid column name collisions
    val location: Location_,

    @Embedded(prefix = "current_") // Prefix to avoid column name collisions
    val current: CurrentWeather
)

// Location remains mostly the same, but ensure all fields are database-friendly
data class Location_(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tz_id: String,
    val localtime_epoch: Int,
    val localtime: String
)

// Current needs to embed Condition
data class CurrentWeather( // Renamed to avoid confusion with original Current
    val last_updated_epoch: Int,
    val last_updated: String,
    val temp_c: Double,
    val temp_f: Double,
    val is_day: Int,

    @Embedded(prefix = "condition_") // Prefix for Condition fields
    val condition: Condition_,

    val wind_mph: Double,
    val wind_kph: Double,
    val wind_degree: Int,
    val wind_dir: String,
    val pressure_mb: Int,
    val pressure_in: Double,
    val precip_mm: Double,
    val precip_in: Double,
    val humidity: Int,
    val cloud: Int,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val windchill_c: Double,
    val windchill_f: Double,
    val heatindex_c: Double,
    val heatindex_f: Double,
    val dewpoint_c: Double,
    val dewpoint_f: Double,
    val vis_km: Double,
    val vis_miles: Double,
    val uv: Double,
    val gust_mph: Double,
    val gust_kph: Double
)

// Condition remains the same
data class Condition_(
    val text: String,
    val icon: String,
    val code: Int
)

