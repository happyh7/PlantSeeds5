package com.bps.plantseeds5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
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

    NavHost(navController = navController, startDestination = "seeds") {
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