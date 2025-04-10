# Clean Architecture i PlantSeeds

Detta dokument beskriver hur Clean Architecture är implementerad i PlantSeeds-projektet.

## 1. Översikt

### 1.1 Arkitekturprinciper
- Separation of Concerns (SoC)
- Dependency Rule
- Single Responsibility Principle (SRP)
- Interface Segregation Principle (ISP)
- Dependency Inversion Principle (DIP)

### 1.2 Arkitekturlager
1. **Presentation Layer**
   - UI-komponenter
   - ViewModels
   - State Management
   - Navigation

2. **Domain Layer**
   - Use Cases
   - Entities
   - Repositories Interfaces
   - Domain Models

3. **Data Layer**
   - Repositories Implementations
   - Data Sources
   - Data Models
   - Mappers

## 2. Presentation Layer

### 2.1 Komponenter
```kotlin
// Exempel på en ViewModel
@HiltViewModel
class PlantListViewModel @Inject constructor(
    private val getPlantsUseCase: GetPlantsUseCase,
    private val deletePlantUseCase: DeletePlantUseCase
) : ViewModel() {
    // State management
    private val _state = MutableStateFlow(PlantListState())
    val state: StateFlow<PlantListState> = _state.asStateFlow()

    // Events
    fun onEvent(event: PlantListEvent) {
        when (event) {
            is PlantListEvent.LoadPlants -> loadPlants()
            is PlantListEvent.DeletePlant -> deletePlant(event.plantId)
        }
    }
}
```

### 2.2 State Management
- Använder StateFlow för reaktivt state management
- Immutable states
- Unidirectional data flow
- Event-driven architecture

### 2.3 Navigation
```kotlin
// Exempel på navigation
@Composable
fun PlantSeedsNavHost(
    navController: NavHostController,
    startDestination: String = Screen.PlantList.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.PlantList.route) {
            PlantListScreen()
        }
        composable(Screen.PlantDetail.route) {
            PlantDetailScreen()
        }
    }
}
```

## 3. Domain Layer

### 3.1 Use Cases
```kotlin
// Exempel på en Use Case
class GetPlantsUseCase @Inject constructor(
    private val plantRepository: PlantRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Plant>>> {
        return plantRepository.getPlants()
    }
}
```

### 3.2 Entities
```kotlin
// Exempel på en Entity
data class Plant(
    val id: String,
    val name: String,
    val description: String,
    val plantingDate: LocalDate,
    val harvestDate: LocalDate?,
    val status: PlantStatus,
    val gardenId: String,
    val seedId: String
)
```

### 3.3 Repository Interfaces
```kotlin
// Exempel på ett Repository Interface
interface PlantRepository {
    suspend fun getPlants(): Flow<Resource<List<Plant>>>
    suspend fun getPlantById(id: String): Flow<Resource<Plant>>
    suspend fun createPlant(plant: Plant): Flow<Resource<Unit>>
    suspend fun updatePlant(plant: Plant): Flow<Resource<Unit>>
    suspend fun deletePlant(id: String): Flow<Resource<Unit>>
}
```

## 4. Data Layer

### 4.1 Repository Implementations
```kotlin
// Exempel på en Repository Implementation
class PlantRepositoryImpl @Inject constructor(
    private val localDataSource: PlantLocalDataSource,
    private val remoteDataSource: PlantRemoteDataSource,
    private val plantMapper: PlantMapper
) : PlantRepository {
    override suspend fun getPlants(): Flow<Resource<List<Plant>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val localPlants = localDataSource.getPlants()
                emit(Resource.Success(localPlants.map { plantMapper.toDomain(it) }))
                
                val remotePlants = remoteDataSource.getPlants()
                localDataSource.savePlants(remotePlants)
                emit(Resource.Success(remotePlants.map { plantMapper.toDomain(it) }))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An error occurred"))
            }
        }
    }
}
```

### 4.2 Data Sources
```kotlin
// Exempel på en Local Data Source
interface PlantLocalDataSource {
    suspend fun getPlants(): List<PlantEntity>
    suspend fun savePlants(plants: List<PlantEntity>)
    suspend fun deletePlant(id: String)
}
```

### 4.3 Data Models
```kotlin
// Exempel på en Data Model
@Entity(
    tableName = "plants",
    foreignKeys = [
        ForeignKey(
            entity = GardenEntity::class,
            parentColumns = ["id"],
            childColumns = ["garden_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PlantEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    @ColumnInfo(name = "planting_date") val plantingDate: Long,
    @ColumnInfo(name = "harvest_date") val harvestDate: Long?,
    val status: String,
    @ColumnInfo(name = "garden_id") val gardenId: String,
    @ColumnInfo(name = "seed_id") val seedId: String
)
```

### 4.4 Mappers
```kotlin
// Exempel på en Mapper
class PlantMapper @Inject constructor() {
    fun toDomain(entity: PlantEntity): Plant {
        return Plant(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            plantingDate = LocalDate.ofEpochDay(entity.plantingDate),
            harvestDate = entity.harvestDate?.let { LocalDate.ofEpochDay(it) },
            status = PlantStatus.valueOf(entity.status),
            gardenId = entity.gardenId,
            seedId = entity.seedId
        )
    }

    fun toEntity(domain: Plant): PlantEntity {
        return PlantEntity(
            id = domain.id,
            name = domain.name,
            description = domain.description,
            plantingDate = domain.plantingDate.toEpochDay(),
            harvestDate = domain.harvestDate?.toEpochDay(),
            status = domain.status.name,
            gardenId = domain.gardenId,
            seedId = domain.seedId
        )
    }
}
```

## 5. Dependency Injection

### 5.1 Hilt Modules
```kotlin
// Exempel på en Hilt Module
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePlantRepository(
        localDataSource: PlantLocalDataSource,
        remoteDataSource: PlantRemoteDataSource,
        plantMapper: PlantMapper
    ): PlantRepository {
        return PlantRepositoryImpl(localDataSource, remoteDataSource, plantMapper)
    }
}
```

### 5.2 Scopes
- SingletonComponent för app-livslängd
- ActivityComponent för activity-livslängd
- ViewModelComponent för ViewModel-livslängd

## 6. Error Handling

### 6.1 Error Types
```kotlin
sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String) : Resource<T>()
    class Loading<T> : Resource<T>()
}

sealed class DatabaseException : Exception() {
    object NotFound : DatabaseException()
    object AlreadyExists : DatabaseException()
    data class Unknown(override val message: String) : DatabaseException()
}
```

### 6.2 Error Handling Strategy
1. Local first approach
2. Fallback to remote
3. Error propagation
4. User feedback

## 7. Testing Strategy

### 7.1 Unit Tests
- Testa Use Cases
- Testa Repository Implementations
- Testa Mappers
- Testa ViewModels

### 7.2 Integration Tests
- Testa Data Sources
- Testa Repository Integration
- Testa Navigation

### 7.3 UI Tests
- Testa Screens
- Testa User Flows
- Testa State Management

## 8. Nästa steg

När Clean Architecture är implementerad, gå vidare till:
1. [Module Structure](02_Module_Structure.md)
2. [Feature Implementation](03_Feature_Implementation.md) 