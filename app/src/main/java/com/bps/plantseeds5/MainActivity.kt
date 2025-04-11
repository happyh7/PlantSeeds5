package com.bps.plantseeds5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bps.plantseeds5.ui.plants.PlantsScreen
import com.bps.plantseeds5.ui.plants.PlantsViewModel
import com.bps.plantseeds5.ui.seeds.AddSeedScreen
import com.bps.plantseeds5.ui.seeds.SeedsScreen
import com.bps.plantseeds5.ui.seeds.SeedsViewModel
import com.bps.plantseeds5.ui.theme.PlantSeeds5Theme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlantSeeds5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val plantsViewModel: PlantsViewModel = hiltViewModel()
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Frön") },
                    label = { Text("Frön") },
                    selected = selectedItem == 0,
                    onClick = {
                        selectedItem = 0
                        navController.navigate("seeds") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Add, contentDescription = "Lägg till") },
                    label = { Text("Lägg till") },
                    selected = selectedItem == 1,
                    onClick = {
                        selectedItem = 1
                        navController.navigate("add_seed") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Inställningar") },
                    label = { Text("Inställningar") },
                    selected = selectedItem == 2,
                    onClick = {
                        selectedItem = 2
                        navController.navigate("settings") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "seeds",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("seeds") {
                SeedsScreen(
                    onSeedClick = { /* TODO */ },
                    onAddSeed = { navController.navigate("add_seed") }
                )
            }
            composable("add_seed") {
                AddSeedScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }
            composable("plants") {
                PlantsScreen(
                    plants = plantsViewModel.plants,
                    plantDao = plantsViewModel.plantDao
                )
            }
            composable("settings") {
                // TODO: Implement settings screen
                Text("Settings")
            }
        }
    }
}