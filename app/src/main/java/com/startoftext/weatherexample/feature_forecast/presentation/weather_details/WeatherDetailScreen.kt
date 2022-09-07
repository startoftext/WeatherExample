package com.startoftext.weatherexample.feature_forecast.presentation.weather_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.startoftext.weatherexample.feature_forecast.presentation.weather_details.components.ForecastIncrement
import com.startoftext.weatherexample.feature_forecast.presentation.weather_details.util.WeatherIconResolver
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun WeatherDetailScreen(
    navController: NavController,
    viewModel: WeatherDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val timeFormatter = DateTimeFormatter.ofPattern("ha").withZone(ZoneId.systemDefault())
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(state.title) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->

        SwipeRefresh(
            state = rememberSwipeRefreshState(state.loadingForecast || state.loadingWeather),
            onRefresh = { viewModel.onEvent(WeatherDetailUiEvent.Refresh) }) {

            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                state.currentWeather?.let { weather ->
                    Row(modifier = Modifier.padding(16.dp)) {
                        Column() {
                            Text(text = state.currentDate)
                            Text(
                                style = MaterialTheme.typography.h1,
                                text = String.format("%.0f", weather.temp) + Char(
                                    0x00B0
                                )
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Column() {
                            Icon(
                                painter = painterResource(id = WeatherIconResolver.resolve(weather.icon)),
                                contentDescription = "",
                                modifier = Modifier.size(128.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))

                LazyRow() {
                    items(state.fiveDayForecast.keys.toList()) {
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(4.dp)) {
                                Text(text = it.toString())
                                Row() {
                                    state.fiveDayForecast[it]?.forEach {
                                        ForecastIncrement(
                                            temp = it.temp,
                                            date = timeFormatter.format(it.date.toInstant()),
                                            WeatherIconResolver.resolve(it.icon),
                                            it.precipitationPercentage
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
}
