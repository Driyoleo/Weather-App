package com.example.weather_rever

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class LocationUtils(val context : Context) {

    private var _fusedLocationProvider : FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun requestLocationUpdates(viewModel: AppViewModel){
        val locationcallback = object : LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let {
                    val location = LocationDataClass(it.longitude , it.latitude)
                    viewModel.setLocationData(location)
                    viewModel.getWeatherData("${it.latitude},${it.longitude}")
                }
            }
        }

        val  locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY ,
            1000
        ).build()

        _fusedLocationProvider.requestLocationUpdates(locationRequest , locationcallback , Looper.getMainLooper())
    }


    fun checkLocationPermission(context: Context) : Boolean {
        if (
            ContextCompat.checkSelfPermission(context , Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
            &&
            ContextCompat.checkSelfPermission(context , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ){
            return true
        }
        else{
            return false
        }
    }
}