package com.startoftext.weatherexample.feature_forecast.presentation.weather_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.startoftext.weatherexample.R

@Composable
fun ForecastIncrement(temp: Double, date: String, icon: Int, precipitation: Double) {
    Column(
        Modifier
            .padding(4.dp)
    ) {
        Icon(
            modifier = Modifier.align(CenterHorizontally),
            painter = painterResource(id = icon),
            contentDescription = ""
        )
        val precipitationString =
            if (precipitation > 0) String.format("%.0f%%", precipitation * 100) else ""
        Text(
            modifier = Modifier.align(CenterHorizontally),
            text = precipitationString
        )
        Text(
            modifier = Modifier.align(CenterHorizontally),
            text = temp.toInt().toString() + Char(0x00B0)
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
    ForecastIncrement(100.0, "8AM", R.drawable.snow, 0.2)
}