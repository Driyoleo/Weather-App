package com.example.weather_rever


data class WeatherData(
    val location: Location,
    val current: Current
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tz_id: String,
    val localtime_epoch: Int,
    val localtime: String,
)

data class Current(
    val last_updated_epoch: Int,
    val last_updated: String,
    val temp_c: Double,
    val temp_f: Double,
    val is_day: Int,
    val condition: Condition,
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

data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)



fun getDummyWeatherData(): WeatherData {
    val dummyLocation = Location(
        name = "London",
        region = "City of London, Greater London",
        country = "United Kingdom",
        lat = 51.5171,
        lon = -0.1062,
        tz_id = "Europe/London",
        localtime_epoch = 1745824578,
        localtime = "2025-04-28 08:16"
    )

    val dummyCondition = Condition(
        text = "Partly Cloudy",
        icon = "//cdn.weatherapi.com/weather/64x64/day/116.png",
        code = 1003
    )

    val dummyCurrent = Current(
        last_updated_epoch = 1745824500,
        last_updated = "2025-04-28 08:15",
        temp_c = 12.1,
        temp_f = 53.8,
        is_day = 1,
        condition = dummyCondition ,
        wind_mph = 2.2,
        wind_kph = 3.6,
        wind_degree = 188,
        wind_dir = "S",
        pressure_mb = 1027,
        pressure_in = 30.33,
        precip_mm = 0.0,
        precip_in = 0.0,
        humidity = 77,
        cloud = 0,
        feelslike_c = 12.6,
        feelslike_f = 54.6,
        windchill_c = 15.3,
        windchill_f = 59.5,
        heatindex_c = 15.3,
        heatindex_f = 59.5,
        dewpoint_c = 9.3,
        dewpoint_f = 48.8,
        vis_km = 10.0,
        vis_miles = 6.0,
        uv = 0.0,
        gust_mph = 6.7,
        gust_kph = 10.8
    )

    return WeatherData(
        location = dummyLocation,
        current = dummyCurrent ,

        )
}
