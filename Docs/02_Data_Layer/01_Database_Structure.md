# Databasstruktur

Detta dokument beskriver databasstrukturen för PlantSeeds-projektet.

## 1. Frö (Seed)

### 1.1 SeedEntity
```kotlin
@Entity(tableName = "seeds")
data class SeedEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val variety: String?, // Ex: "Cherry", "Beefsteak" för tomater
    val scientificName: String?,
    val description: String,
    val category: String, // Ex: Grönsaker, Blommor, Örter
    val subCategory: String?, // Ex: Rotfrukter, Bladgrönsaker
    val source: String?, // Var fröet kommer ifrån
    val packagingDate: LocalDate?, // När fröet packeterades
    val expiryDate: LocalDate?, // När fröet går ut
    val germinationRate: Float?, // Grobarhet i procent
    val plantingSeason: List<String>, // Vår, Sommar, Höst
    val harvestSeason: List<String>, // Vår, Sommar, Höst
    val daysToGermination: Int?,
    val daysToHarvest: Int?,
    val optimalTemperature: TemperatureRange?,
    val soilType: List<String>, // Sandig, Lera, Mogen kompost
    val sunExposure: SunExposure, // Full sun, Partial shade, Shade
    val waterNeeds: WaterNeeds, // Low, Medium, High
    val spacing: Spacing?, // Avstånd mellan plantor
    val depth: Float?, // Planteringsdjup i cm
    val height: Float?, // Förväntad höjd i cm
    val width: Float?, // Förväntad bredd i cm
    val isPerennial: Boolean,
    val isIndoorSuitable: Boolean,
    val isOutdoorSuitable: Boolean,
    val difficultyLevel: DifficultyLevel, // Easy, Medium, Hard
    val imageUrl: String?,
    val tips: List<String>,
    val commonPests: List<String>,
    val companionPlants: List<String>,
    val avoidPlants: List<String>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

data class TemperatureRange(
    val min: Float, // Minsta temperatur i Celsius
    val max: Float  // Högsta temperatur i Celsius
)

data class Spacing(
    val betweenPlants: Float, // Avstånd mellan plantor i cm
    val betweenRows: Float?   // Avstånd mellan rader i cm
)

enum class SunExposure {
    FULL_SUN,
    PARTIAL_SHADE,
    SHADE
}

enum class WaterNeeds {
    LOW,
    MEDIUM,
    HIGH
}

enum class DifficultyLevel {
    EASY,
    MEDIUM,
    HARD
}
```

### 1.2 SeedMetadata
```kotlin
@Entity(tableName = "seed_metadata")
data class SeedMetadata(
    @PrimaryKey
    val seedId: String,
    val popularity: Int, // Antal gånger fröet har valts
    val successRate: Float, // Framgångsandel i procent
    val averageRating: Float,
    val reviewCount: Int,
    val lastPlanted: LocalDateTime?,
    val isFeatured: Boolean,
    val tags: List<String>,
    val averageGerminationRate: Float?, // Genomsnittlig grobarhet från användare
    val averageYield: Float?, // Genomsnittlig skörd i kg
    val averageTimeToHarvest: Int? // Genomsnittlig tid till skörd i dagar
)
```

## 2. Växt (Plant)

### 2.1 PlantEntity
```kotlin
@Entity(tableName = "plants")
data class PlantEntity(
    @PrimaryKey
    val id: String,
    val gardenId: String,
    val seedId: String,
    val name: String,
    val description: String?,
    val quantity: Int, // Antal plantor
    val containerSize: ContainerSize?, // Storlek på behållare
    val plantingDate: LocalDateTime,
    val expectedHarvestDate: LocalDateTime?,
    val actualHarvestDate: LocalDateTime?,
    val status: PlantStatus,
    val location: Location?,
    val notes: List<PlantNote>,
    val images: List<String>,
    val healthStatus: HealthStatus,
    val growthStage: GrowthStage,
    val lastWatered: LocalDateTime?,
    val lastFertilized: LocalDateTime?,
    val lastPruned: LocalDateTime?,
    val fertilizerType: String?, // Typ av gödselmedel
    val fertilizerSchedule: FertilizerSchedule?, // Schema för gödsling
    val wateringSchedule: WateringSchedule?, // Schema för vattning
    val customAttributes: Map<String, String>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

data class Location(
    val latitude: Double,
    val longitude: Double,
    val area: String? // Ex: "Köksträdgården", "Balkongen"
)

data class PlantNote(
    val id: String,
    val content: String,
    val type: NoteType,
    val createdAt: LocalDateTime
)

data class ContainerSize(
    val diameter: Float, // Diameter i cm
    val depth: Float, // Djup i cm
    val volume: Float, // Volym i liter
    val material: String? // Material (plast, terrakotta, etc.)
)

data class FertilizerSchedule(
    val type: String,
    val frequency: Frequency,
    val amount: Float,
    val unit: String,
    val nextApplication: LocalDateTime?
)

data class WateringSchedule(
    val frequency: Frequency,
    val amount: Float,
    val unit: String,
    val timeOfDay: TimeOfDay,
    val nextWatering: LocalDateTime?
)

enum class PlantStatus {
    PLANNED,
    PLANTED,
    GERMINATING,
    GROWING,
    FLOWERING,
    FRUITING,
    HARVESTED,
    FAILED
}

enum class HealthStatus {
    HEALTHY,
    WARNING,
    CRITICAL,
    DEAD
}

enum class GrowthStage {
    SEED,
    SEEDLING,
    VEGETATIVE,
    FLOWERING,
    FRUITING,
    MATURE
}

enum class NoteType {
    GENERAL,
    CARE,
    OBSERVATION,
    PROBLEM,
    SOLUTION
}

enum class Frequency {
    DAILY,
    WEEKLY,
    BIWEEKLY,
    MONTHLY,
    CUSTOM
}

enum class TimeOfDay {
    MORNING,
    AFTERNOON,
    EVENING,
    NIGHT
}
```

### 2.2 PlantCareLog
```kotlin
@Entity(tableName = "plant_care_logs")
data class PlantCareLog(
    @PrimaryKey
    val id: String,
    val plantId: String,
    val type: CareType,
    val description: String,
    val amount: Float?, // Ex: vattenmängd i liter
    val unit: String?, // Ex: "L", "g", "ml"
    val notes: String?,
    val images: List<String>?,
    val weatherConditions: WeatherConditions?, // Väder vid skötsel
    val toolsUsed: List<String>?, // Använda verktyg
    val cost: Float?, // Kostnad i SEK
    val timeSpent: Duration?, // Tidsåtgång
    val createdAt: LocalDateTime
)

data class WeatherConditions(
    val temperature: Float?, // Temperatur i Celsius
    val humidity: Float?, // Luftfuktighet i procent
    val weather: String?, // Väderbeskrivning
    val windSpeed: Float?, // Vindhastighet i m/s
    val precipitation: Float? // Nederbörd i mm
)

enum class CareType {
    WATERING,
    FERTILIZING,
    PRUNING,
    PEST_CONTROL,
    DISEASE_TREATMENT,
    TRANSPLANTING,
    HARVESTING,
    OTHER
}
```

## 3. Relationer

### 3.1 Foreign Keys
```kotlin
// SeedEntity har inga foreign keys

// PlantEntity har följande foreign keys:
@ForeignKey(
    entity = GardenEntity::class,
    parentColumns = ["id"],
    childColumns = ["gardenId"],
    onDelete = ForeignKey.CASCADE
)
val gardenId: String

@ForeignKey(
    entity = SeedEntity::class,
    parentColumns = ["id"],
    childColumns = ["seedId"],
    onDelete = ForeignKey.SET_NULL
)
val seedId: String

// PlantCareLog har följande foreign key:
@ForeignKey(
    entity = PlantEntity::class,
    parentColumns = ["id"],
    childColumns = ["plantId"],
    onDelete = ForeignKey.CASCADE
)
val plantId: String
```

## 4. Index

### 4.1 Seed Indexes
```kotlin
@Index(value = ["name"])
@Index(value = ["category"])
@Index(value = ["plantingSeason"])
@Index(value = ["harvestSeason"])
@Index(value = ["variety"])
@Index(value = ["source"])
```

### 4.2 Plant Indexes
```kotlin
@Index(value = ["gardenId"])
@Index(value = ["seedId"])
@Index(value = ["status"])
@Index(value = ["plantingDate"])
@Index(value = ["expectedHarvestDate"])
@Index(value = ["healthStatus"])
@Index(value = ["growthStage"])
```

## 5. Datatyper

### 5.1 Type Converters
```kotlin
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let { LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault()) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? {
        return date?.atZone(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
    }

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(",")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return value.split(",")
    }

    @TypeConverter
    fun fromStringMap(value: Map<String, String>): String {
        return value.entries.joinToString(";") { "${it.key}:${it.value}" }
    }

    @TypeConverter
    fun toStringMap(value: String): Map<String, String> {
        return value.split(";").associate {
            val (key, value) = it.split(":")
            key to value
        }
    }

    @TypeConverter
    fun fromDuration(value: Duration?): Long? {
        return value?.toMillis()
    }

    @TypeConverter
    fun toDuration(value: Long?): Duration? {
        return value?.let { Duration.ofMillis(it) }
    }
}
```

## 6. Databasversionering

### 6.1 Migrations
```kotlin
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE seeds ADD COLUMN isIndoorSuitable INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE seeds ADD COLUMN isOutdoorSuitable INTEGER NOT NULL DEFAULT 1")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS seed_metadata (seedId TEXT PRIMARY KEY, popularity INTEGER NOT NULL DEFAULT 0, successRate REAL NOT NULL DEFAULT 0, averageRating REAL NOT NULL DEFAULT 0, reviewCount INTEGER NOT NULL DEFAULT 0, lastPlanted INTEGER, isFeatured INTEGER NOT NULL DEFAULT 0)")
    }
}

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE seeds ADD COLUMN variety TEXT")
        database.execSQL("ALTER TABLE seeds ADD COLUMN source TEXT")
        database.execSQL("ALTER TABLE seeds ADD COLUMN packagingDate INTEGER")
        database.execSQL("ALTER TABLE seeds ADD COLUMN expiryDate INTEGER")
        database.execSQL("ALTER TABLE seeds ADD COLUMN germinationRate REAL")
        
        database.execSQL("ALTER TABLE plants ADD COLUMN quantity INTEGER NOT NULL DEFAULT 1")
        database.execSQL("ALTER TABLE plants ADD COLUMN containerSize TEXT")
        database.execSQL("ALTER TABLE plants ADD COLUMN fertilizerType TEXT")
        database.execSQL("ALTER TABLE plants ADD COLUMN fertilizerSchedule TEXT")
        database.execSQL("ALTER TABLE plants ADD COLUMN wateringSchedule TEXT")
        
        database.execSQL("ALTER TABLE plant_care_logs ADD COLUMN weatherConditions TEXT")
        database.execSQL("ALTER TABLE plant_care_logs ADD COLUMN toolsUsed TEXT")
        database.execSQL("ALTER TABLE plant_care_logs ADD COLUMN cost REAL")
        database.execSQL("ALTER TABLE plant_care_logs ADD COLUMN timeSpent INTEGER")
    }
}
```

## 7. Best Practices

### 7.1 Databasdesign
- Använd primärnycklar för alla entiteter
- Implementera korrekta foreign key-relationer
- Skapa index för vanliga sökningar
- Använd TypeConverters för komplexa datatyper
- Planera för framtida utökningar

### 7.2 Prestanda
- Optimera frågor
- Använd paginering för stora dataset
- Implementera caching där lämpligt
- Undvik N+1-problem

### 7.3 Underhåll
- Dokumentera alla ändringar
- Skapa backup-strategi
- Implementera datarensning
- Planera för migreringar 