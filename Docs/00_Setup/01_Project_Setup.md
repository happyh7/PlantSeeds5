# Projektkonfiguration

Detta dokument beskriver hur man sätter upp PlantSeeds-projektet från grunden.

## 1. Skapa nytt Android-projekt

1. Öppna Android Studio
2. Välj "New Project"
3. Välj "Empty Activity"
4. Konfigurera projektet:
   - Name: PlantSeeds
   - Package name: com.bps.plantseeds3
   - Language: Kotlin
   - Minimum SDK: API 26 (Android 8.0)
   - Use legacy android.support libraries: Nej

## 2. Konfigurera Gradle

### Root build.gradle.kts
```kotlin
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
        classpath("com.google.gms:google-services:4.4.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
```

### settings.gradle.kts
```kotlin
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PlantSeeds"
include(":app")
include(":modules:core:common")
include(":modules:core:data")
include(":modules:core:domain")
include(":modules:feature:garden")
include(":modules:feature:plant")
include(":modules:feature:seed")
include(":modules:feature:social")
include(":modules:feature:marketplace")
```

## 3. Konfigurera App-modulen

### app/build.gradle.kts
```kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.bps.plantseeds3"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bps.plantseeds3"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Project modules
    implementation(project(":modules:core:data"))
    implementation(project(":modules:core:domain"))
    implementation(project(":modules:core:common"))
    implementation(project(":modules:feature:garden"))
    implementation(project(":modules:feature:plant"))
    implementation(project(":modules:feature:seed"))
    implementation(project(":modules:feature:social"))
    implementation(project(":modules:feature:marketplace"))
    
    // Core Android
    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    // Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    ksp("com.google.dagger:hilt-android-compiler:2.48")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")

    // WorkManager
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.hilt:hilt-work:1.1.0")
    ksp("androidx.hilt:hilt-compiler:1.1.0")

    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
```

## 4. Skapa modulstruktur

Skapa följande mappstruktur i projektet:

```
PlantSeeds/
├── app/
├── modules/
│   ├── core/
│   │   ├── common/
│   │   ├── data/
│   │   └── domain/
│   └── feature/
│       ├── garden/
│       ├── plant/
│       ├── seed/
│       ├── social/
│       └── marketplace/
```

## 5. Konfigurera varje modul

För varje modul, skapa en build.gradle.kts-fil med lämpliga beroenden. Se respektive moduls dokumentation för detaljer.

## 6. Verifiering

Efter att ha konfigurerat projektet, verifiera att:

1. Projektet kan byggas utan fel
2. Alla moduler är korrekt konfigurerade
3. Beroenden är korrekt uppsatta
4. Gradle-synkronisering fungerar
5. Android Studio kan indexera projektet

## 7. Nästa steg

När projektkonfigurationen är klar, gå vidare till:
1. [Environment Setup](02_Environment_Setup.md)
2. [Dependencies](03_Dependencies.md) 