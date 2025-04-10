package com.bps.plantseeds5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bps.plantseeds5.ui.theme.PlantSeeds5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlantSeeds5Theme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Frön", "Lägg till", "Inställningar")

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            when (index) {
                                0 -> Icon(Icons.AutoMirrored.Filled.List, contentDescription = item)
                                1 -> Icon(Icons.Filled.Add, contentDescription = item)
                                2 -> Icon(Icons.Filled.Settings, contentDescription = item)
                            }
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            when (index) {
                                0 -> navController.navigate("seeds")
                                1 -> navController.navigate("add")
                                2 -> navController.navigate("settings")
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "seeds",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("seeds") { SeedsScreen() }
            composable("add") { AddSeedScreen() }
            composable("settings") { SettingsScreen() }
        }
    }
}

@Composable
fun SeedsScreen() {
    Text("Lista över frön kommer här")
}

@Composable
fun AddSeedScreen() {
    Text("Formulär för att lägga till frö kommer här")
}

@Composable
fun SettingsScreen() {
    Text("Inställningar kommer här")
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    PlantSeeds5Theme {
        MainScreen()
    }
}