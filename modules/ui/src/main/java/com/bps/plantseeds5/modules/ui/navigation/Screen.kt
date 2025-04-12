package com.bps.plantseeds5.modules.ui.navigation

sealed class Screen(val route: String) {
    object Seeds : Screen("seeds")
    object AddSeed : Screen("add_seed")
    object Plants : Screen("plants")
    object Settings : Screen("settings")
} 