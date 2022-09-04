package com.startoftext.weatherexample.feature_forcast.presentation.locations.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.common.util.CollectionUtils.setOf
import com.startoftext.weatherexample.feature_forcast.domain.model.Forecast
import com.startoftext.weatherexample.feature_forcast.domain.model.Location
import com.startoftext.weatherexample.feature_forcast.domain.model.LocationAndForecast

@ExperimentalMaterialApi
@Composable
fun LocationItem(
    location: LocationAndForecast,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit
){
    val dismissState = rememberDismissState(initialValue = DismissValue.Default)

    SwipeToDismiss(
        state = dismissState,
        background = {
            MaterialTheme.colors.background
        },
            directions = setOf(DismissDirection.StartToEnd)
            ) {

        Box(
            modifier = Modifier.clip(RoundedCornerShape(10.dp))
        ){
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(end = 32.dp)
            ){
                Text(
                    text = location.location.name,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = location.forecast.tempCurrent.toString(),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )

                }

            }
        }
}


@ExperimentalMaterialApi
@Preview
@Composable
fun ComposablePreview() {
    LocationItem(location = LocationAndForecast(Location(id = 1, latitude = 0.0, longitude = 0.0, name = "Denver, CO"), Forecast(0, 90.0)), onDeleteClick = {})
}