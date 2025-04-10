# Enhetstester

Här dokumenteras alla enhetstester i applikationen.

## Innehåll
- [PlantTests](PlantTests.md) - Tester för växthantering
- [SeedTests](SeedTests.md) - Tester för fröhantering
- [GardenTests](GardenTests.md) - Tester för trädgårdshantering
- [UserTests](UserTests.md) - Tester för användarhantering

## Exempel på enhetstest
```kotlin
class PlantListViewModelTest {
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: PlantListViewModel
    private lateinit var getPlants: GetPlantsUseCase

    @Before
    fun setup() {
        getPlants = mockk()
        viewModel = PlantListViewModel(getPlants)
    }

    @Test
    fun `when loading plants, should show loading state`() = runTest {
        // Given
        coEvery { getPlants() } returns emptyList()

        // When
        viewModel.loadPlants()

        // Then
        assertTrue(viewModel.isLoading.value)
    }

    @Test
    fun `when plants loaded successfully, should update plants list`() = runTest {
        // Given
        val expectedPlants = listOf(
            Plant(id = "1", name = "Test Plant")
        )
        coEvery { getPlants() } returns expectedPlants

        // When
        viewModel.loadPlants()

        // Then
        assertEquals(expectedPlants, viewModel.plants.value)
    }
}
```

## Översikt
Enhetstester verifierar att enskilda komponenter fungerar som förväntat. De är snabba att köra och isolerade från andra delar av systemet. 