package com.startoftext.weatherexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.common.util.CollectionUtils.listOf
import com.startoftext.weatherexample.feature_forecast.presentation.locations.LocationsScreen
import com.startoftext.weatherexample.feature_forecast.presentation.util.Screen
import com.startoftext.weatherexample.feature_forecast.presentation.weather_details.WeatherDetailScreen
import com.startoftext.weatherexample.ui.theme.WeatherExampleTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherExampleTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ){
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LocationsScreen.route
                    ){
                        composable(route = Screen.LocationsScreen.route) {
                            LocationsScreen(navController = navController)
                        }
                        composable(route = Screen.WeatherDetailScreen.route +
                                "?locationId={locationId}",
                            arguments = listOf(
                                navArgument(
                                    name = "locationId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            WeatherDetailScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun MyScreen(locationsViewModel: LocationsViewModel = hiltViewModel()) {
//    val context = LocalContext.current
//    val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, locationsViewModel.field).build(context)
//    val scaffoldState = rememberScaffoldState()
//
//    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//
//        if(it.resultCode == RESULT_OK){
//            val place = Autocomplete.getPlaceFromIntent(it.data)
//                    val latLng = place.latLng
//                    Log.d("place LatLng: ", "$latLng")
//        }
//
//    }
//
//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(modifier = Modifier,
//                onClick = {
//                    launcher.launch(intent)
//                }) {
//                Icon(imageVector = Icons.Default.Search, contentDescription =
//                "")
//            }
//        },
//        scaffoldState = scaffoldState
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(MaterialTheme.colors.onSurface)
//                .padding(16.dp)
//        ) {
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//        }
//    }
//}
