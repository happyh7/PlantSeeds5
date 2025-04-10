# Navigering

Här dokumenteras navigeringsstrukturen i applikationen.

## Innehåll
- [MainNavGraph](MainNavGraph.md) - Huvudnavigeringsgraf
- [PlantNavGraph](PlantNavGraph.md) - Navigering för växter
- [SeedNavGraph](SeedNavGraph.md) - Navigering för frön
- [GardenNavGraph](GardenNavGraph.md) - Navigering för trädgårdar

## Exempel på navigeringsstruktur
```kotlin
@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "plants"
    ) {
        composable("plants") {
            PlantListScreen(
                onPlantClick = { plant ->
                    navController.navigate("plant/${plant.id}")
                }
            )
        }
        composable(
            route = "plant/{plantId}",
            arguments = listOf(
                navArgument("plantId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val plantId = backStackEntry.arguments?.getString("plantId")
            PlantDetailScreen(
                plantId = plantId,
                onBack = { navController.popBackStack() }
            )
        }
        // ... fler destinations
    }
}
```

## Översikt
Navigeringen hanterar flödet mellan olika skärmar i applikationen. Den använder Navigation Compose för att definiera och hantera navigeringsgrafen. 