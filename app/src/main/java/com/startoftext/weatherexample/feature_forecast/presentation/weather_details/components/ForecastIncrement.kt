package com.startoftext.weatherexample.feature_forecast.presentation.weather_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ForecastIncrement(temp: Double, date: String) {
    Column() {
        Icon(
            modifier = Modifier.align(CenterHorizontally),
            imageVector = Icons.Default.Delete,
            contentDescription = ""
        )
        Text(
            modifier = Modifier.align(CenterHorizontally),
            text = temp.toInt().toString()
        )
        Text(
            modifier = Modifier.align(CenterHorizontally),
            text = date
        )
    }
}

@Preview
@Composable
fun preview() {
    ForecastIncrement(100.0, "8AM")
}