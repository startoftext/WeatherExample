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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.android.gms.common.util.CollectionUtils
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.startoftext.weatherexample.feature_forecast.presentation.locations.components.LocationItem
import com.startoftext.weatherexample.feature_forecast.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
    val fields = CollectionUtils.listOf(Place.Field.NAME, Place.Field.LAT_LNG)
    val intent =
        Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
            .setTypeFilter(TypeFilter.CITIES).build(context)
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val place = Autocomplete.getPlaceFromIntent(it.data)
                val latLng = place.latLng
                Log.d("place LatLng: ", "$latLng")
                viewModel.onEvent(
                    LocationsEvent.AddLocation(
                        name = place.name,
                        longitude = latLng.longitude,
                        latitude = latLng.latitude
                    )
                )
            }
        }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is LocationsViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
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
        topBar = {
            TopAppBar(
                title = { Text("Weather") }
            )
        },
        scaffoldState = scaffoldState,

        ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            SwipeRefresh(
                state = rememberSwipeRefreshState(state.loading),
                onRefresh = { viewModel.onEvent(LocationsEvent.Refresh) },
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.locations, key = { it.location.id!! }) { it ->
                        LocationItem(
                            location = it.location,
                            temp = it.forecast?.temp?.toInt(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.surface)
                                .clickable {
                                    navController.navigate(
                                        Screen.WeatherDetailScreen.route + "?locationId=${it.location.id}"
                                    )
                                },
                            onDeleteClick = {
                                viewModel.onEvent(LocationsEvent.DeleteLocation(it.location))
                                scope.launch {
                                    val result = scaffoldState.snackbarHostState.showSnackbar(
                                        message = "City deleted",
                                        actionLabel = "Undo"
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        viewModel.onEvent(LocationsEvent.RestoreLocation)
                                    }
                                }
                            }
                        )
                    }
                    item { Spacer(modifier = Modifier.padding(48.dp)) }
                }
            }
        }
    }
}


