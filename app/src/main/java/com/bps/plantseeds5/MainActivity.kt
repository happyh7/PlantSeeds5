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
import com.bps.plantseeds5.modules.ui.navigation.Screen
import com.bps.plantseeds5.modules.ui.plants.PlantsScreen
import com.bps.plantseeds5.modules.ui.plants.PlantsViewModel
import com.bps.plantseeds5.modules.ui.seeds.AddSeedScreen
import com.bps.plantseeds5.modules.ui.seeds.SeedsScreen
import com.bps.plantseeds5.modules.ui.seeds.SeedsViewModel
import com.bps.plantseeds5.modules.ui.theme.PlantSeeds5Theme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.lifecycle.compose.collectAsStateWithLifecycle

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
    val plants by plantsViewModel.plants.collectAsStateWithLifecycle(initialValue = emptyList())

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Frön") },
                    label = { Text("Frön") },
                    selected = selectedItem == 0,
                    onClick = {
                        selectedItem = 0
                        navController.navigate(Screen.Seeds.route) {
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
                        navController.navigate(Screen.AddSeed.route) {
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
                        navController.navigate(Screen.Settings.route) {
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
            startDestination = Screen.Seeds.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Seeds.route) {
                SeedsScreen(
                    onSeedClick = { /* TODO */ },
                    onAddSeed = { navController.navigate(Screen.AddSeed.route) }
                )
            }
            composable(Screen.AddSeed.route) {
                AddSeedScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }
            composable(Screen.Plants.route) {
                PlantsScreen(
                    plants = plants,
                    onAddPlant = { /* TODO */ }
                )
            }
            composable(Screen.Settings.route) {
                // TODO: Implement settings screen
                Text("Settings")
            }
        }
    }
}