# Responsiv Design och Skärmanpassning

## 1. Översikt

Detta dokument beskriver hur PlantSeeds anpassar sig till olika skärmstorlekar och formfaktorer, inklusive vanliga telefoner, foldables, och surfplattor.

## 2. Skärmstorlekar och Formfaktorer

### 2.1 Telefoner
- Vanliga telefoner (16:9, 18:9, 19.5:9)
- Foldables (in- och utvikbara skärmar)
- Olika skärmstorlekar (4-7 tum)

### 2.2 Surfplattor
- Kompakt (7-8 tum)
- Standard (9-10 tum)
- Stor (11+ tum)
- Olika aspect ratios

### 2.3 Foldables
- Invikbara telefoner
- Utvikbara telefoner
- Dubbelskärmsenheter

## 3. Implementering

### 3.1 Skärmdetektering
```kotlin
// Exempel på skärmdetektering
class ScreenState {
    val windowSizeClass: WindowSizeClass
    val foldState: FoldState
    val orientation: Orientation
}

@Composable
fun PlantSeedsApp() {
    val screenState = rememberScreenState()
    
    // Anpassa layout baserat på skärmstorlek
    when (screenState.windowSizeClass) {
        WindowSizeClass.Compact -> CompactLayout()
        WindowSizeClass.Medium -> MediumLayout()
        WindowSizeClass.Expanded -> ExpandedLayout()
    }
    
    // Hantera fold/unfold
    LaunchedEffect(screenState.foldState) {
        when (screenState.foldState) {
            FoldState.Folded -> handleFoldedState()
            FoldState.Unfolded -> handleUnfoldedState()
        }
    }
}
```

### 3.2 Adaptiva Layouts
```kotlin
// Exempel på adaptiv layout
@Composable
fun PlantList(
    plants: List<Plant>,
    windowSizeClass: WindowSizeClass
) {
    when (windowSizeClass) {
        WindowSizeClass.Compact -> {
            // En kolumn för små skärmar
            LazyColumn {
                items(plants) { plant ->
                    PlantCard(plant)
                }
            }
        }
        WindowSizeClass.Medium -> {
            // Två kolumner för medelstora skärmar
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(plants) { plant ->
                    PlantCard(plant)
                }
            }
        }
        WindowSizeClass.Expanded -> {
            // Tre kolumner för stora skärmar
            LazyVerticalGrid(
                columns = GridCells.Fixed(3)
            ) {
                items(plants) { plant ->
                    PlantCard(plant)
                }
            }
        }
    }
}
```

### 3.3 Foldable-specifika funktioner
```kotlin
// Exempel på foldable-anpassning
@Composable
fun PlantDetail(
    plant: Plant,
    foldState: FoldState
) {
    if (foldState == FoldState.Unfolded) {
        // Utökad vy för utvikbart läge
        ExpandedPlantDetail(plant)
    } else {
        // Kompakt vy för hopviket läge
        CompactPlantDetail(plant)
    }
}

@Composable
fun ExpandedPlantDetail(plant: Plant) {
    Row {
        // Bild på vänster sida
        PlantImage(plant)
        // Detaljer på höger sida
        PlantInfo(plant)
    }
}
```

### 3.4 Surfplattespecifika vyer
```kotlin
// Exempel på surfplatteanpassning
@Composable
fun PlantSeedsTabletLayout() {
    Row {
        // Sidomeny för surfplattor
        NavigationRail {
            // Navigeringslänkar
        }
        
        // Huvudinnehåll
        PlantList()
        
        // Detaljvy (om vald)
        PlantDetail()
    }
}
```

### 3.5 Prestandaoptimering
```kotlin
// Exempel på prestandaoptimering
@Composable
fun OptimizedPlantImage(
    plant: Plant,
    screenDensity: Float
) {
    // Anpassa bildkvalitet baserat på skärmdensitet
    val imageQuality = when {
        screenDensity > 3.0f -> ImageQuality.High
        screenDensity > 2.0f -> ImageQuality.Medium
        else -> ImageQuality.Low
    }
    
    // Ladda anpassade resurser
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(plant.imageUrl)
            .size(Size.ORIGINAL)
            .quality(imageQuality)
            .build(),
        contentDescription = plant.name,
        modifier = Modifier.fillMaxWidth()
    )
}

// Exempel på resursoptimering
@Composable
fun ResourceOptimizer() {
    val configuration = LocalConfiguration.current
    val screenDensity = configuration.densityDpi
    
    // Anpassa cache-storlek baserat på enhet
    val cacheSize = when (screenDensity) {
        in 0..320 -> CacheSize.Small
        in 321..480 -> CacheSize.Medium
        else -> CacheSize.Large
    }
    
    // Anpassa minneshantering
    LaunchedEffect(screenDensity) {
        optimizeMemoryUsage(cacheSize)
    }
}
```

### 3.6 Skärmdensitetshantering
```kotlin
// Exempel på skärmdensitetshantering
@Composable
fun DensityAwareComponent() {
    val configuration = LocalConfiguration.current
    val screenDensity = configuration.densityDpi
    
    // Anpassa storlekar baserat på densitet
    val textSize = when (screenDensity) {
        in 0..320 -> 14.sp
        in 321..480 -> 16.sp
        else -> 18.sp
    }
    
    // Anpassa padding och marginaler
    val padding = when (screenDensity) {
        in 0..320 -> 8.dp
        in 321..480 -> 12.dp
        else -> 16.dp
    }
    
    Text(
        text = "Plant Information",
        fontSize = textSize,
        modifier = Modifier.padding(padding)
    )
}
```

## 4. Användarupplevelse

### 4.1 Smidiga övergångar
- Animera layoutändringar
- Behåll kontext vid skärmsväng
- Anpassa innehållsflöde

### 4.2 Optimering
- Ladda anpassade resurser
- Använd rätt layout för varje skärm
- Optimera prestanda

### 4.3 Tillgänglighet
- Anpassa tillgänglighetsinställningar
- Behåll tillgänglighetsfunktioner
- Testa på alla enheter

### 4.4 Prestandaoptimering
- Anpassa bildkvalitet efter skärmdensitet
- Optimera minnesanvändning
- Implementera effektiv caching
- Anpassa resursladdning
- Optimera layoutberäkningar
- Hantera skärmdensitet effektivt

## 5. Testning

### 5.1 Enhetstestning
- Testa olika skärmstorlekar
- Verifiera layoutanpassning
- Testa fold/unfold beteende

### 5.2 Användartestning
- Testa på olika enheter
- Samla feedback
- Iterativ förbättring

## 6. Bästa praxis

### 6.1 Design
- Använd Material Design 3
- Följ Android formfaktorriktlinjer
- Implementera adaptiva komponenter

### 6.2 Utveckling
- Använd Jetpack Compose
- Implementera WindowSizeClass
- Följ foldable-riktlinjer

### 6.3 Underhåll
- Regelbundna uppdateringar
- Anpassning för nya enheter
- Prestandaoptimering

## 7. Checklista

### 7.1 Grundläggande krav
- [ ] Anpassning för olika skärmstorlekar
- [ ] Stöd för foldables
- [ ] Optimering för surfplattor
- [ ] Responsiva layouts
- [ ] Smidiga övergångar
- [ ] Prestandaoptimering
- [ ] Tillgänglighetsanpassning

### 7.2 Avancerade krav
- [ ] Adaptiva komponenter
- [ ] Fold/unfold detection
- [ ] Enhetsspecifika vyer
- [ ] Resursoptimering
- [ ] Omfattande testning
- [ ] Användarfeedback
- [ ] Kontinuerlig förbättring 