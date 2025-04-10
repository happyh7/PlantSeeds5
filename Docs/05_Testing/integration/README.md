# Integrationstester

Här dokumenteras alla integrationstester i applikationen.

## Innehåll
- [DatabaseTests](DatabaseTests.md) - Tester för databasintegration
- [NetworkTests](NetworkTests.md) - Tester för nätverksintegration
- [RepositoryTests](RepositoryTests.md) - Tester för repository-integration
- [ViewModelTests](ViewModelTests.md) - Tester för ViewModel-integration

## Exempel på integrationstest
```kotlin
@RunWith(AndroidJUnit4::class)
class PlantRepositoryIntegrationTest {
    private lateinit var database: AppDatabase
    private lateinit var repository: PlantRepositoryImpl

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        repository = PlantRepositoryImpl(database.plantDao())
    }

    @Test
    fun `when plant is inserted, should be able to retrieve it`() = runTest {
        // Given
        val plant = Plant(
            id = "1",
            name = "Test Plant",
            species = "Test Species"
        )

        // When
        repository.insertPlant(plant)
        val retrievedPlant = repository.getPlant("1")

        // Then
        assertEquals(plant, retrievedPlant)
    }

    @After
    fun tearDown() {
        database.close()
    }
}
```

## Översikt
Integrationstester verifierar att olika delar av systemet fungerar tillsammans som förväntat. De testar interaktionen mellan komponenter. 