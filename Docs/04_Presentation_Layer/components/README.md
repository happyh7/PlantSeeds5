# UI-komponenter

Här dokumenteras alla återanvändbara UI-komponenter i applikationen.

## Innehåll
- [PlantCard](PlantCard.md) - Kort för att visa växtinformation
- [SeedCard](SeedCard.md) - Kort för att visa fröinformation
- [GardenCard](GardenCard.md) - Kort för att visa trädgårdsinformation
- [WeatherCard](WeatherCard.md) - Kort för att visa väderinformation

## Exempel på komponentstruktur
```kotlin
@Composable
fun PlantCard(
    plant: Plant,
    onPlantClick: (Plant) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onPlantClick(plant) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = plant.name,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = plant.species,
                style = MaterialTheme.typography.body2
            )
            // ... fler detaljer
        }
    }
}
```

## Översikt
UI-komponenter är återanvändbara byggstenar för användargränssnittet. De följer Material Design-principer och är responsiva. 