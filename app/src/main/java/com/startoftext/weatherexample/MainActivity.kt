package com.startoftext.weatherexample

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.common.util.CollectionUtils.listOf
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.startoftext.weatherexample.feature_forcast.presentation.locations.LocationsScreen
import com.startoftext.weatherexample.feature_forcast.presentation.locations.LocationsViewModel
import com.startoftext.weatherexample.feature_forcast.presentation.util.Screen
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
                        composable(route = Screen.LocationsScreen.route){
                            LocationsScreen(navController = navController)
                        }
//                        composable(route = Screen.AddEditNoteScreen.route +
//                                "?locationId={locationId}",
//                            arguments = listOf(
//                                navArgument(
//                                    name = "locationId"
//                                ){
//                                    type = NavType.IntType
//                                    defaultValue = -1
//                                }
//                            )
//                        ){
//
//                        }
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
