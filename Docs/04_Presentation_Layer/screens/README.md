# Skärmar

Här dokumenteras alla skärmar i applikationen.

## Innehåll
- [PlantListScreen](PlantListScreen.md) - Lista över växter
- [PlantDetailScreen](PlantDetailScreen.md) - Detaljerad vy för växt
- [SeedListScreen](SeedListScreen.md) - Lista över frön
- [GardenScreen](GardenScreen.md) - Trädgårdsvy

## Exempel på skärmstruktur
```kotlin
@Composable
fun PlantListScreen(
    viewModel: PlantListViewModel = hiltViewModel(),
    onPlantClick: (Plant) -> Unit
) {
    val plants by viewModel.plants.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mina Växter") },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Default.Add, contentDescription = "Lägg till växt")
                    }
                }
            )
        }
    ) { padding ->
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(plants) { plant ->
                    PlantCard(
                        plant = plant,
                        onPlantClick = onPlantClick
                    )
                }
            }
        }
    }
}
```

## Översikt
Skärmar är de huvudsakliga vyerna i applikationen. De sammanställer UI-komponenter och hanterar användarinteraktioner. 