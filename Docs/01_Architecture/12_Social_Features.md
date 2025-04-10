# Sociala funktioner i PlantSeeds

## 1. Översikt

Detta dokument beskriver hur sociala funktioner och delning av trädgårdsdata implementeras i PlantSeeds, inklusive betygssystem och synkronisering av populära växter.

## 2. Funktioner

### 2.1 Delning av trädgårdsdata
- Dela enskilda växter eller hela trädgårdar
- Offentliga och privata inställningar
- Kontroll över vilken information som delas
- Hantering av delade resurser

### 2.2 Betygssystem
- Stjärnbetyg för växter och frön
- Kommentarsfunktion
- "Gilla"-funktion
- Popularitetsranking

### 2.3 Följande-system
- Följa specifika användare
- Anpassningsbara notifieringar
- Trädgårdsuppdateringsflöde
- Filtrering av uppdateringar

### 2.4 Synkronisering
- Automatisk synkronisering av populära växter
- Valbar synkronisering av specifika växter
- Hantering av konflikter
- Offline-tillgänglighet för synkroniserade data

## 3. Implementering

### 3.1 Datamodeller
```kotlin
// Exempel på delad växt
@Entity(tableName = "shared_plants")
data class SharedPlantEntity(
    @PrimaryKey val id: String,
    val originalOwnerId: String,
    val name: String,
    val description: String,
    val careInstructions: String,
    val averageRating: Float,
    val totalRatings: Int,
    val totalLikes: Int,
    val isPublic: Boolean,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "updated_at") val updatedAt: Long
)

// Exempel på betyg
@Entity(tableName = "ratings")
data class RatingEntity(
    @PrimaryKey val id: String,
    val plantId: String,
    val userId: String,
    val rating: Int,
    val comment: String?,
    @ColumnInfo(name = "created_at") val createdAt: Long
)

// Exempel på följande
@Entity(tableName = "follows")
data class FollowEntity(
    @PrimaryKey val id: String,
    val followerId: String,
    val followingId: String,
    @ColumnInfo(name = "created_at") val createdAt: Long
)

// Exempel på notifieringsinställningar
@Entity(tableName = "notification_settings")
data class NotificationSettingsEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val followedUserId: String,
    val notifyOnNewPlants: Boolean,
    val notifyOnUpdates: Boolean,
    val notifyOnHarvests: Boolean,
    val notifyOnComments: Boolean,
    @ColumnInfo(name = "updated_at") val updatedAt: Long
)
```

### 3.2 Synkroniseringslogik
```kotlin
// Exempel på synkroniseringshanterare för delade växter
class SharedPlantSyncManager @Inject constructor(
    private val sharedPlantRepository: SharedPlantRepository,
    private val networkMonitor: NetworkMonitor
) {
    suspend fun syncPopularPlants() {
        if (networkMonitor.isConnected()) {
            val popularPlants = sharedPlantRepository.getPopularPlants()
            popularPlants.forEach { plant ->
                try {
                    sharedPlantRepository.syncPlant(plant)
                } catch (e: Exception) {
                    // Hantera synkroniseringsfel
                }
            }
        }
    }
}
```

### 3.3 Betygshantering
```kotlin
// Exempel på betygshanterare
class RatingManager @Inject constructor(
    private val ratingRepository: RatingRepository
) {
    suspend fun ratePlant(plantId: String, userId: String, rating: Int, comment: String?) {
        val newRating = Rating(
            id = UUID.randomUUID().toString(),
            plantId = plantId,
            userId = userId,
            rating = rating,
            comment = comment,
            createdAt = System.currentTimeMillis()
        )
        ratingRepository.addRating(newRating)
        updatePlantAverageRating(plantId)
    }
    
    private suspend fun updatePlantAverageRating(plantId: String) {
        val ratings = ratingRepository.getRatingsForPlant(plantId)
        val average = ratings.map { it.rating }.average()
        sharedPlantRepository.updatePlantRating(plantId, average.toFloat(), ratings.size)
    }
}
```

### 3.4 Följande och notifieringar
```kotlin
// Exempel på följande-hanterare
class FollowManager @Inject constructor(
    private val followRepository: FollowRepository,
    private val notificationManager: NotificationManager
) {
    suspend fun followUser(followerId: String, followingId: String) {
        val follow = Follow(
            id = UUID.randomUUID().toString(),
            followerId = followerId,
            followingId = followingId,
            createdAt = System.currentTimeMillis()
        )
        followRepository.addFollow(follow)
        notificationManager.setupDefaultNotificationSettings(followerId, followingId)
    }
    
    suspend fun unfollowUser(followerId: String, followingId: String) {
        followRepository.removeFollow(followerId, followingId)
        notificationManager.removeNotificationSettings(followerId, followingId)
    }
}

// Exempel på notifieringshanterare
class NotificationManager @Inject constructor(
    private val notificationSettingsRepository: NotificationSettingsRepository,
    private val pushNotificationService: PushNotificationService
) {
    suspend fun updateNotificationSettings(
        userId: String,
        followedUserId: String,
        settings: NotificationSettings
    ) {
        notificationSettingsRepository.updateSettings(userId, followedUserId, settings)
    }
    
    suspend fun notifyGardenUpdate(update: GardenUpdate) {
        val followers = followRepository.getFollowers(update.userId)
        followers.forEach { follower ->
            val settings = notificationSettingsRepository.getSettings(follower.id, update.userId)
            if (shouldNotify(settings, update.type)) {
                pushNotificationService.sendNotification(
                    userId = follower.id,
                    title = "Ny uppdatering från ${update.userName}",
                    message = createUpdateMessage(update),
                    type = update.type
                )
            }
        }
    }
    
    private fun shouldNotify(settings: NotificationSettings, updateType: UpdateType): Boolean {
        return when (updateType) {
            UpdateType.NEW_PLANT -> settings.notifyOnNewPlants
            UpdateType.PLANT_UPDATE -> settings.notifyOnUpdates
            UpdateType.HARVEST -> settings.notifyOnHarvests
            UpdateType.COMMENT -> settings.notifyOnComments
        }
    }
}
```

## 4. Användarupplevelse

### 4.1 Delning
- Enkel delning av växter och trädgårdar
- Kontroll över synlighet
- Hantering av delade resurser
- Notifieringar vid interaktioner

### 4.2 Betyg och interaktion
- Intuitivt betygssystem
- Möjlighet att kommentera
- "Gilla"-funktion
- Filtrering efter popularitet

### 4.3 Följande och uppdateringar
- Enkel följning av användare
- Anpassningsbara notifieringsinställningar
- Filtrering av uppdateringar
- Trädgårdsuppdateringsflöde
- Möjlighet att temporärt pausa notifieringar

### 4.4 Synkronisering
- Automatisk synkronisering av populära växter
- Valbar synkronisering
- Offline-tillgänglighet
- Konfliktlösning

## 5. Säkerhet och Dataskydd

### 5.1 Dataskydd
- Kryptering av delade data
- Kontroll över synlighet
- Hantering av personlig information
- GDPR-compliance

### 5.2 Moderering
- Rapporteringssystem
- Automatisk filtrering
- Moderatorverktyg
- Användarblockering

### 5.3 Notifieringskontroll
- Användarkontroll över notifieringar
- Möjlighet att blockera användare
- Begränsning av notifieringsfrekvens
- Spam-skydd

## 6. Prestandaoptimering

### 6.1 Caching
- Smart cache för populära växter
- Prioritering av ofta använda data
- Automatisk rensning
- Optimering av lagringsutrymme

### 6.2 Synkronisering
- Deltasynkronisering
- Batch-uppdateringar
- Komprimering av data
- Smart nätverksanvändning

### 6.3 Notifieringsoptimering
- Batch-hantering av notifieringar
- Smart notifieringsprioritering
- Begränsning av notifieringsfrekvens
- Optimering av push-meddelanden

## 7. Testning

### 7.1 Funktionstestning
- Testa delningsfunktioner
- Verifiera betygssystem
- Testa synkronisering
- Verifiera moderering
- Testa följande-system
- Verifiera notifieringsinställningar
- Testa uppdateringsflöde
- Verifiera filtrering

### 7.2 Prestandatestning
- Mät synkroniseringstid
- Testa cacheprestanda
- Verifiera minnesanvändning
- Testa batteripåverkan
- Mät notifieringsprestanda
- Testa batch-hantering
- Verifiera minnesanvändning
- Testa batteripåverkan

## 8. Underhåll och Support

### 8.1 Övervakning
- Loggning av delningsaktivitet
- Övervakning av betygssystem
- Spårning av synkronisering
- Analys av användarinteraktioner
- Loggning av följande-aktivitet
- Övervakning av notifieringssystem
- Spårning av uppdateringsflöde
- Analys av användarinteraktioner

### 8.2 Felsökning
- Diagnostikverktyg
- Loggning av fel
- Användarstöd
- Återställningsprocedurer
- Diagnostik för notifieringsproblem
- Loggning av följande-fel
- Användarstöd för notifieringsinställningar
- Återställningsprocedurer 