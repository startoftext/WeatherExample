package com.startoftext.weatherexample.feature_forecast.presentation.locations

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.startoftext.weatherexample.feature_forecast.presentation.locations.components.LocationItem
import com.startoftext.weatherexample.feature_forecast.presentation.util.Screen

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun LocationsScreen(
    navController: NavController,
    viewModel: LocationsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, viewModel.field).build(context)
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if(it.resultCode == Activity.RESULT_OK) {
            val place = Autocomplete.getPlaceFromIntent(it.data)
            val latLng = place.latLng
            Log.d("place LatLng: ", "$latLng")
            viewModel.onEvent(
                LocationsUiEvent.AddLocation(
                    place.name,
                    latLng.longitude,
                    latLng.latitude
                )
            )
        }
    }

            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { launcher.launch(intent) },
                        backgroundColor = MaterialTheme.colors.primary
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add location")
                    }
                },
                scaffoldState = scaffoldState,

                ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Locations",
                            style = MaterialTheme.typography.h4
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    SwipeRefresh(
                        // TODO fix this
                        state = rememberSwipeRefreshState(false),
                        onRefresh = { viewModel.onEvent(LocationsUiEvent.Refresh) },
                    ) {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(state.locations, key = { it.location.id!! }) { location ->
                                LocationItem(
                                    location = location,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(MaterialTheme.colors.surface)
                                        .clickable {
                                            navController.navigate(
                                                Screen.WeatherDetailScreen.route + "?locationId=${location.location.id}"
                                            )
                                        },
                                    onDeleteClick = {
                                        viewModel.onEvent(LocationsUiEvent.DeleteLocation(location.location))
                                    }
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                }
            }
        }


