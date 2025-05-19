package com.example.weather_rever

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat

@Composable
fun HomeScreen(context: Context, modifier: Modifier = Modifier, innerpadding : PaddingValues, viewModel: AppViewModel , locationUtils: LocationUtils) {


    val location = viewModel.locationData.value





    val requestpermissionlauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions->
            if(
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
                &&
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
            ){
                // we have location
                locationUtils.requestLocationUpdates(viewModel)
            }
            else{
                // request
                val rationaleRequest = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity ,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity ,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )

                if (rationaleRequest){
                    Toast.makeText(
                        context ,
                        "it is required for this feature" ,
                        Toast.LENGTH_LONG
                    ).show()
                }
                else{
                    Toast.makeText(
                        context ,
                        "go to settings and enable location permission" ,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }


        }
    )
    if (locationUtils.checkLocationPermission(context)){
        // we have permission
        locationUtils.requestLocationUpdates(viewModel)
    }
    else{
        viewModel.getWeatherData("London")
    }



    val roomData = viewModel._weatherRoomData.collectAsState(initial = emptyList())





    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.dodger_blue))
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            when{
                viewModel.responseState.value.loading->{
                    if (roomData == emptyList<WeatherDataEntity>()){
                        Column(
                            modifier = Modifier.fillMaxSize() ,
                            verticalArrangement = Arrangement.Center ,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text("Loading..." , fontWeight = FontWeight.Bold , fontSize = 20.sp)
                        }
                    }
                    else{
                        var weatherdata = roomData.value.get(0)

                        Column (
                            modifier = Modifier.fillMaxSize().padding(innerpadding)
                        ){
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(50.dp , 100.dp)
                                ,
                                verticalArrangement = Arrangement.Center ,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                Text(weatherdata.location.name , color = Color.White , fontWeight = FontWeight.Bold , fontSize = 20.sp)
                                Text(weatherdata.location.region , color = Color.White)
                                Text(weatherdata.location.country , color = Color.White)


                                Button(
                                    onClick = {
                                        if (locationUtils.checkLocationPermission(context)){
                                            // we have permission
                                            locationUtils.requestLocationUpdates(viewModel)
                                        }
                                        else{
                                            // get permissions
                                            requestpermissionlauncher.launch(
                                                arrayOf(
                                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                                )
                                            )
                                        }
                                    }
                                ){
                                    Text("Refresh")
                                }
                            }
                            Column (
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                                    .background(Color.White.copy(0.08f) , RoundedCornerShape(8.dp))
                            ){
                                Column (
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp)
                                ){
                                    Text("Last Updated : ${weatherdata.current.last_updated}" , color = Color.White )
                                    HorizontalDivider(
                                        modifier = Modifier.padding(8.dp) ,
                                        color = Color.White.copy(alpha = 0.3f)
                                    )
                                    Text("Temperature : ${weatherdata.current.temp_c}°C , ${weatherdata.current.temp_f} F" , color = Color.White)
                                    Spacer(Modifier.height(8.dp))
                                    Text("Wind : ${weatherdata.current.wind_kph} km/h , ${weatherdata.current.wind_mph} mph" , color = Color.White)
                                    Spacer(Modifier.height(8.dp))
                                    Text("Humidity : ${weatherdata.current.humidity}%" , color = Color.White)
                                    Spacer(Modifier.height(8.dp))
                                    Text("Cloud : ${weatherdata.current.cloud}%" , color = Color.White)
                                    Spacer(Modifier.height(8.dp))
                                    Text("UV : ${weatherdata.current.uv}" , color = Color.White)
                                    Spacer(Modifier.height(8.dp))
                                    Text("Gust : ${weatherdata.current.gust_kph} km/h , ${weatherdata.current.gust_mph} mph" , color = Color.White)
                                    Spacer(Modifier.height(8.dp))
                                    Text("Condition : ${weatherdata.current.condition.text}" , color = Color.White)
                                    Spacer(Modifier.height(8.dp))
                                    Text("Feels Like : ${weatherdata.current.feelslike_c}°C , ${weatherdata.current.feelslike_f} F" , color = Color.White)
                                    Spacer(Modifier.height(8.dp))
                                    Text("Visibility : ${weatherdata.current.vis_km} km , ${weatherdata.current.vis_miles} miles" , color = Color.White)
                                    Spacer(Modifier.height(8.dp))
                                    Text("Pressure : ${weatherdata.current.pressure_mb} mb , ${weatherdata.current.pressure_in} in" , color = Color.White)
                                    Spacer(Modifier.height(8.dp))
                                    Text("Precip : ${weatherdata.current.precip_mm} mm , ${weatherdata.current.precip_in} in" , color = Color.White)
                                    Spacer(Modifier.height(8.dp))
                                    Text("Dew Point : ${weatherdata.current.dewpoint_c}°C , ${weatherdata.current.dewpoint_f} F" , color = Color.White)
                                }
                            }
                        }
                    }
                }
                viewModel.responseState.value.error != null ->{
                    Column(
                        modifier = Modifier.fillMaxSize() ,
                        verticalArrangement = Arrangement.Center ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(viewModel.responseState.value.error!!, fontWeight = FontWeight.Bold , fontSize = 20.sp)
                    }
                }
                else->{
                    var weatherdata = viewModel.responseState.value.data

                    Column (
                        modifier = Modifier.fillMaxSize().padding(innerpadding)
                    ){
                        Column (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(50.dp , 100.dp)
                            ,
                            verticalArrangement = Arrangement.Center ,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(weatherdata.location.name , color = Color.White , fontWeight = FontWeight.Bold , fontSize = 20.sp)
                            Text(weatherdata.location.region , color = Color.White)
                            Text(weatherdata.location.country , color = Color.White)


                            Button(
                                onClick = {
                                    if (locationUtils.checkLocationPermission(context)){
                                        // we have permission
                                        locationUtils.requestLocationUpdates(viewModel)
                                    }
                                    else{
                                        // get permissions
                                        requestpermissionlauncher.launch(
                                            arrayOf(
                                                Manifest.permission.ACCESS_FINE_LOCATION,
                                                Manifest.permission.ACCESS_COARSE_LOCATION
                                            )
                                        )
                                    }
                                }
                            ){
                              Text("Refresh")
                            }
                        }
                        Column (
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .background(Color.White.copy(0.08f) , RoundedCornerShape(8.dp))
                        ){
                            Column (
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            ){
                                Text("Last Updated : ${weatherdata.current.last_updated}" , color = Color.White )
                                HorizontalDivider(
                                    modifier = Modifier.padding(8.dp) ,
                                    color = Color.White.copy(alpha = 0.3f)
                                )
                                Text("Temperature : ${weatherdata.current.temp_c}°C , ${weatherdata.current.temp_f} F" , color = Color.White)
                                Spacer(Modifier.height(8.dp))
                                Text("Wind : ${weatherdata.current.wind_kph} km/h , ${weatherdata.current.wind_mph} mph" , color = Color.White)
                                Spacer(Modifier.height(8.dp))
                                Text("Humidity : ${weatherdata.current.humidity}%" , color = Color.White)
                                Spacer(Modifier.height(8.dp))
                                Text("Cloud : ${weatherdata.current.cloud}%" , color = Color.White)
                                Spacer(Modifier.height(8.dp))
                                Text("UV : ${weatherdata.current.uv}" , color = Color.White)
                                Spacer(Modifier.height(8.dp))
                                Text("Gust : ${weatherdata.current.gust_kph} km/h , ${weatherdata.current.gust_mph} mph" , color = Color.White)
                                Spacer(Modifier.height(8.dp))
                                Text("Condition : ${weatherdata.current.condition.text}" , color = Color.White)
                                Spacer(Modifier.height(8.dp))
                                Text("Feels Like : ${weatherdata.current.feelslike_c}°C , ${weatherdata.current.feelslike_f} F" , color = Color.White)
                                Spacer(Modifier.height(8.dp))
                                Text("Visibility : ${weatherdata.current.vis_km} km , ${weatherdata.current.vis_miles} miles" , color = Color.White)
                                Spacer(Modifier.height(8.dp))
                                Text("Pressure : ${weatherdata.current.pressure_mb} mb , ${weatherdata.current.pressure_in} in" , color = Color.White)
                                Spacer(Modifier.height(8.dp))
                                Text("Precip : ${weatherdata.current.precip_mm} mm , ${weatherdata.current.precip_in} in" , color = Color.White)
                                Spacer(Modifier.height(8.dp))
                                Text("Dew Point : ${weatherdata.current.dewpoint_c}°C , ${weatherdata.current.dewpoint_f} F" , color = Color.White)
                            }
                        }
                    }

                }
            }
        }

    }
}


