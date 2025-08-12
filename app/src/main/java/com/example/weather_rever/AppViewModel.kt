package com.example.weather_rever

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AppViewModel(private val weatherRepository: RoomRepository = Graph.weatherRepository) : ViewModel() {

    // Room Database
    lateinit var _weatherRoomData : Flow<List<WeatherDataEntity>>

    init {
        viewModelScope.launch {
            _weatherRoomData = weatherRepository.getWeatherData()
        }
    }

    fun addWeatherData(weatherData: WeatherData) {
        viewModelScope.launch {
            val weatherDataEntity = apiDataToRoomData(weatherData)
            weatherRepository.addWeatherData(weatherDataEntity)
        }
    }

    fun updateWeatherData(weatherData: WeatherData) {
        viewModelScope.launch {
            val weatherDataEntity = apiDataToRoomData(weatherData)
            weatherRepository.updateWeatherData(weatherDataEntity)
        }
    }

    fun clearWeatherData() {
        viewModelScope.launch {
            weatherRepository.clearWeatherData()
        }
    }










    // API Service
    private var _responseState = mutableStateOf(ResponseState())
    var responseState : State<ResponseState> = _responseState
    private var _locationData = mutableStateOf<LocationDataClass?>(null)
    var locationData : State<LocationDataClass?> = _locationData
    private var key = "" // add your api key..





    fun setLocationData(locationData : LocationDataClass){
        _locationData.value = locationData
    }



    fun getWeatherData(location : String) {
        viewModelScope.launch {
            try {

                val respose = apiService.getCurrentWeather(key , location)
                _responseState.value = _responseState.value.copy(
                    loading = false ,
                    data = respose ,
                    error = null
                )
                clearWeatherData()
                addWeatherData(respose)

            }catch (e : Exception){
                _responseState.value = _responseState.value.copy(
                    loading = false ,
                    error = e.message
                )
            }
        }
    }

    fun resetweatherdata(){
        _responseState.value = _responseState.value.copy(
            loading = true
        )
    }

    data class ResponseState(
        var loading : Boolean = true,
        var data : WeatherData = getDummyWeatherData(),
        var error : String? = null
    )



    fun apiDataToRoomData(weatherData: WeatherData) : WeatherDataEntity{
        return WeatherDataEntity(
            location = Location_(
                name = weatherData.location.name,
                region = weatherData.location.region,
                country = weatherData.location.country,
                lat = weatherData.location.lat ,
                lon = weatherData.location.lon ,
                tz_id = weatherData.location.tz_id ,
                localtime_epoch = weatherData.location.localtime_epoch ,
                localtime = weatherData.location.localtime
            ) ,
            current = CurrentWeather(
                last_updated_epoch = weatherData.current.last_updated_epoch,
                last_updated = weatherData.current.last_updated,
                temp_c = weatherData.current.temp_c,
                temp_f = weatherData.current.temp_f,
                is_day = weatherData.current.is_day,
                condition = Condition_(
                    text = weatherData.current.condition.text,
                    icon = weatherData.current.condition.icon,
                    code = weatherData.current.condition.code
                ),
                wind_mph = weatherData.current.wind_mph,
                wind_kph = weatherData.current.wind_kph,
                wind_degree = weatherData.current.wind_degree,
                wind_dir = weatherData.current.wind_dir,
                pressure_mb = weatherData.current.pressure_mb,
                pressure_in = weatherData.current.pressure_in,
                precip_mm = weatherData.current.precip_mm,
                precip_in = weatherData.current.precip_in,
                humidity = weatherData.current.humidity,
                cloud = weatherData.current.cloud,
                feelslike_c = weatherData.current.feelslike_c,
                feelslike_f = weatherData.current.feelslike_f,
                windchill_c = weatherData.current.windchill_c,
                windchill_f = weatherData.current.windchill_f,
                heatindex_c = weatherData.current.heatindex_c,
                heatindex_f = weatherData.current.heatindex_f ,
                dewpoint_c = weatherData.current.dewpoint_c,
                dewpoint_f = weatherData.current.dewpoint_f,
                vis_km = weatherData.current.vis_km,
                vis_miles = weatherData.current.vis_miles ,
                uv = weatherData.current.uv,
                gust_mph = weatherData.current.gust_mph,
                gust_kph = weatherData.current.gust_kph
            )
        )


    }

    fun roomDataToApiData(weatherData: WeatherDataEntity) : WeatherData{
        return WeatherData(
            location = Location(
                name = weatherData.location.name,
                region = weatherData.location.region,
                country = weatherData.location.country,
                lat = weatherData.location.lat,
                lon = weatherData.location.lon,
                tz_id = weatherData.location.tz_id,
                localtime_epoch = weatherData.location.localtime_epoch,
                localtime = weatherData.location.localtime
            ) ,
            current = Current(
                last_updated_epoch = weatherData.current.last_updated_epoch,
                last_updated = weatherData.current.last_updated,
                temp_c = weatherData.current.temp_c,
                temp_f = weatherData.current.temp_f,
                is_day = weatherData.current.is_day,
                condition = Condition(
                    text = weatherData.current.condition.text,
                    icon = weatherData.current.condition.icon,
                    code = weatherData.current.condition.code
                ),
                wind_mph = weatherData.current.wind_mph,
                wind_kph = weatherData.current.wind_kph,
                wind_degree = weatherData.current.wind_degree,
                wind_dir = weatherData.current.wind_dir ,
                pressure_mb = weatherData.current.pressure_mb,
                pressure_in = weatherData.current.pressure_in ,
                precip_mm = weatherData.current.precip_mm,
                precip_in = weatherData.current.precip_in,
                humidity = weatherData.current.humidity,
                cloud = weatherData.current.cloud,
                feelslike_c = weatherData.current.feelslike_c,
                feelslike_f = weatherData.current.feelslike_f,
                windchill_c = weatherData.current.windchill_c,
                windchill_f = weatherData.current.windchill_f,
                heatindex_c = weatherData.current.heatindex_c,
                heatindex_f = weatherData.current.heatindex_f,
                dewpoint_c = weatherData.current.dewpoint_c,
                dewpoint_f = weatherData.current.dewpoint_f,
                vis_km = weatherData.current.vis_km,
                vis_miles = weatherData.current.vis_miles,
                uv = weatherData.current.uv,
                gust_mph = weatherData.current.gust_mph,
                gust_kph = weatherData.current.gust_kph
            )
        )
    }




}