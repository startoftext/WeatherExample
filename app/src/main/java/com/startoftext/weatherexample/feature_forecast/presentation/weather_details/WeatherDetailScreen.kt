package com.startoftext.weatherexample.feature_forecast.presentation.weather_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.startoftext.weatherexample.feature_forecast.presentation.weather_details.components.ForecastIncrement
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun WeatherDetailScreen(
    viewModel: WeatherDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val fiveDayForecast = state.fiveDayForecast
    val timeFormatter = DateTimeFormatter.ofPattern("ha").withZone(ZoneId.systemDefault())
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(title = { Text("TopAppBar") }) },
    ) { padding ->


        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Row() {

            }
            Spacer(modifier = Modifier.height(32.dp))

            LazyRow() {
                items(state.fiveDayForecast.keys.toList()) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Column() {
                            Text(text = it.toString())
                            Row() {
                                state.fiveDayForecast[it]?.forEach {
                                    ForecastIncrement(
                                        temp = it.temp,
                                        date = timeFormatter.format(it.date.toInstant())
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun preview() {
    WeatherDetailScreen()
}