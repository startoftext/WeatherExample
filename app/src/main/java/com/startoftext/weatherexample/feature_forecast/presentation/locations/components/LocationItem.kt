package com.startoftext.weatherexample.feature_forecast.presentation.locations.components

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
import com.startoftext.weatherexample.feature_forecast.domain.model.Forecast
import com.startoftext.weatherexample.feature_forecast.domain.model.Location
import com.startoftext.weatherexample.feature_forecast.domain.model.LocationAndForecast

@ExperimentalMaterialApi
@Composable
fun LocationItem(
    location: LocationAndForecast,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit
) {
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToEnd) {
                onDeleteClick.invoke()
            }
            true
        }
    )
    
//    if (dismissState.isDismissed(DismissDirection.StartToEnd)) {
//        LaunchedEffect(true) {
//
//            //delay(300)
//            dismissState.reset()
//            dismissState.snapTo(DismissValue.Default)
//        }
//    }

    SwipeToDismiss(
        state = dismissState,
        background = {
            MaterialTheme.colors.secondaryVariant
        },
        directions = setOf(DismissDirection.StartToEnd),
        modifier = modifier.fillMaxSize(),
        dismissThresholds = { FractionalThreshold(0.7f) },
        dismissContent = {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 16.dp, start = 16.dp, top = 8.dp, bottom = 8.dp)
                        .align(Alignment.Center)
                ) {
                    Text(
                        text = location.location.name,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = location.forecast.tempCurrent.toString(),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 10,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Divider(modifier = Modifier.align(Alignment.BottomCenter))
            }

        })
}



@ExperimentalMaterialApi
@Preview
@Composable
fun ComposablePreview() {
    LocationItem(location = LocationAndForecast(Location(id = 1, latitude = 0.0, longitude = 0.0, name = "Denver, CO"), Forecast(0, 90.0)), onDeleteClick = {})
}