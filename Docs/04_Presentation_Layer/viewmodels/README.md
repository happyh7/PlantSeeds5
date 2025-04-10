# ViewModels

Här dokumenteras alla ViewModels i applikationen.

## Innehåll
- [PlantListViewModel](PlantListViewModel.md) - Hantering av växtlista
- [PlantDetailViewModel](PlantDetailViewModel.md) - Hantering av växtdetaljer
- [SeedListViewModel](SeedListViewModel.md) - Hantering av frölista
- [GardenViewModel](GardenViewModel.md) - Hantering av trädgård

## Exempel på ViewModel-struktur
```kotlin
@HiltViewModel
class PlantListViewModel @Inject constructor(
    private val getPlants: GetPlantsUseCase
) : ViewModel() {

    private val _plants = MutableStateFlow<List<Plant>>(emptyList())
    val plants: StateFlow<List<Plant>> = _plants.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadPlants()
    }

    private fun loadPlants() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _plants.value = getPlants()
            } catch (e: Exception) {
                // Hantera fel
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refresh() {
        loadPlants()
    }
}
```

## Översikt
ViewModels hanterar affärslogik och datatillstånd för skärmar. De kommunicerar med domänlagret via use cases. 