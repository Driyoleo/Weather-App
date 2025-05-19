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

    fun addWeatherData(weatherData: WeatherDataEntity) {
        viewModelScope.launch {
            weatherRepository.addWeatherData(weatherData)
        }
    }

    fun updateWeatherData(weatherData: WeatherDataEntity) {
        viewModelScope.launch {
            weatherRepository.updateWeatherData(weatherData)
        }
    }









    // API Service
    private var _responseState = mutableStateOf(ResponseState())
    var responseState : State<ResponseState> = _responseState
    private var _locationData = mutableStateOf<LocationDataClass?>(null)
    var locationData : State<LocationDataClass?> = _locationData
    private var key = "29fd0bb06fba4c26b6f73643250305"





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
                updateWeatherData(respose)

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
        var data : WeatherDataEntity = getDummyWeatherData(),
        var error : String? = null
    )





}