# Beroenden och Versioner

Detta dokument beskriver alla beroenden som används i PlantSeeds-projektet och deras versioner.

## 1. Gradle Versioner

### 1.1 Plugins
```kotlin
plugins {
    id("com.android.application") version "8.2.2"
    id("org.jetbrains.kotlin.android") version "1.9.22"
    id("com.google.devtools.ksp") version "1.9.22-1.0.17"
    id("com.google.dagger.hilt.android") version "2.48"
    id("com.google.gms.google-services") version "4.4.0"
}
```

### 1.2 Build Tools
```kotlin
buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
        classpath("com.google.gms:google-services:4.4.0")
    }
}
```

## 2. Android Beroenden

### 2.1 Core Android
```kotlin
implementation("androidx.core:core-ktx:1.12.0")
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
implementation("androidx.activity:activity-compose:1.8.2")
coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
```

### 2.2 Compose
```kotlin
implementation(platform("androidx.compose:compose-bom:2024.02.00"))
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.ui:ui-graphics")
implementation("androidx.compose.ui:ui-tooling-preview")
implementation("androidx.compose.material3:material3")
implementation("androidx.compose.material:material-icons-extended")
implementation("androidx.navigation:navigation-compose:2.7.7")
implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
```

### 2.3 Room
```kotlin
implementation("androidx.room:room-runtime:2.6.1")
implementation("androidx.room:room-ktx:2.6.1")
ksp("androidx.room:room-compiler:2.6.1")
```

### 2.4 WorkManager
```kotlin
implementation("androidx.work:work-runtime-ktx:2.9.0")
implementation("androidx.hilt:hilt-work:1.1.0")
ksp("androidx.hilt:hilt-compiler:1.1.0")
```

## 3. Tredjepartsberoenden

### 3.1 Hilt
```kotlin
implementation("com.google.dagger:hilt-android:2.48")
ksp("com.google.dagger:hilt-android-compiler:2.48")
```

### 3.2 Firebase
```kotlin
implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
implementation("com.google.firebase:firebase-analytics-ktx")
implementation("com.google.firebase:firebase-auth-ktx")
implementation("com.google.firebase:firebase-firestore-ktx")
implementation("com.google.firebase:firebase-storage-ktx")
```

### 3.3 Coil
```kotlin
implementation("io.coil-kt:coil-compose:2.5.0")
```

## 4. Testberoenden

### 4.1 Unit Tests
```kotlin
testImplementation("junit:junit:4.13.2")
```

### 4.2 Android Tests
```kotlin
androidTestImplementation("androidx.test.ext:junit:1.1.5")
androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.00"))
androidTestImplementation("androidx.compose.ui:ui-test-junit4")
```

### 4.3 Debug
```kotlin
debugImplementation("androidx.compose.ui:ui-tooling")
debugImplementation("androidx.compose.ui:ui-test-manifest")
```

## 5. Versionshantering

### 5.1 Versionskatalog (libs.versions.toml)
```toml
[versions]
agp = "8.2.2"
kotlin = "1.9.22"
ksp = "1.9.22-1.0.17"
hilt = "2.48"
firebase = "4.4.0"
composeBom = "2024.02.00"
navigation = "2.7.7"
room = "2.6.1"
work = "2.9.0"
coil = "2.5.0"
```

### 5.2 Versionsuppdatering
1. Kontrollera nya versioner regelbundet
2. Testa uppdateringar i separat gren
3. Verifiera kompatibilitet mellan beroenden
4. Uppdatera versionskatalogen
5. Uppdatera dokumentationen

## 6. Beroendehantering

### 6.1 Konflikthantering
1. Använd versionskatalog för centraliserad versionshantering
2. Följ beroendeträdet för att identifiera konflikter
3. Lös konflikter genom att uppdatera till kompatibla versioner
4. Dokumentera beslut om versionsval

### 6.2 Säkerhet
1. Kontrollera beroenden för säkerhetsrisker
2. Uppdatera beroenden med säkerhetskorrigeringar
3. Använd signerade artefakter
4. Verifiera SHA-256-hashar

## 7. Nästa steg

När beroendekonfigurationen är klar, gå vidare till:
1. [Clean Architecture](01_Architecture/01_Clean_Architecture.md)
2. [Module Structure](01_Architecture/02_Module_Structure.md) 