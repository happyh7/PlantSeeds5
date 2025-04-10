# Modulstruktur i PlantSeeds

Detta dokument beskriver den detaljerade modulstrukturen i PlantSeeds-projektet.

## 1. Översikt över Moduler

### 1.1 Kärnmoduler (Core Modules)
```
:app
:modules
├── :core
│   ├── :common
│   ├── :data
│   └── :domain
```

### 1.2 Funktionsmoduler (Feature Modules)
```
:modules
├── :features
│   ├── :auth
│   ├── :garden
│   ├── :plant
│   ├── :seed
│   ├── :task
│   ├── :weather
│   ├── :notification
│   ├── :settings
│   ├── :search
│   └── :statistics
```

### 1.3 Stödmoduler (Support Modules)
```
:modules
├── :support
│   ├── :ui
│   ├── :network
│   ├── :database
│   ├── :analytics
│   └── :testing
```

## 2. Kärnmoduler

### 2.1 :app
- Applikationsingångspunkt
- Applikationskonfiguration
- Dependency Injection setup
- Firebase konfiguration
- MainActivity

### 2.2 :core:common
- Delade utilities
- Extensions
- Constants
- Resource wrappers
- Common interfaces

### 2.3 :core:domain
- Use Cases
- Repository interfaces
- Domain models
- Business logic
- Error handling

### 2.4 :core:data
- Repository implementations
- Data sources
- Data models
- Mappers
- Database access

## 3. Funktionsmoduler

### 3.1 :features:auth
- Inloggning/utloggning
- Användarhantering
- Profilhantering
- Autentiseringslogik

### 3.2 :features:garden
- Trädgårdslista
- Trädgårdsdetaljer
- Trädgårdsredigering
- Trädgårdsstatistik

### 3.3 :features:plant
- Växtlista
- Växtdetaljer
- Växtredigering
- Växtstatus
- Växtbilder

### 3.4 :features:seed
- Fröbank
- Frödetaljer
- Fröredigering
- Fröinventering
- Fröbeställning

### 3.5 :features:task
- Uppgiftslista
- Uppgiftsdetaljer
- Uppgiftsredigering
- Påminnelser
- Uppgiftsstatistik

### 3.6 :features:weather
- Väderprognos
- Väderhistorik
- Väderaviseringar
- Väderstatistik

### 3.7 :features:notification
- Notifikationshantering
- Notifikationsinställningar
- Notifikationshistorik
- Push-notifikationer

### 3.8 :features:settings
- Appinställningar
- Användarinställningar
- Språkval
- Tema
- Datasynkronisering

### 3.9 :features:search
- Global sökning
- Filtersökning
- Sökhistorik
- Sökresultat

### 3.10 :features:statistics
- Översiktsstatistik
- Detaljerad statistik
- Rapporter
- Export

## 4. Stödmoduler

### 4.1 :support:ui
- Delade UI-komponenter
- Tema
- Stilar
- Ikoner
- Animeringar

### 4.2 :support:network
- API-klienter
- Nätverkshantering
- Offline support
- Synkronisering

### 4.3 :support:database
- Database setup
- Migreringar
- Backup
- Restore

### 4.4 :support:analytics
- Event tracking
- Användarstatistik
- Crash reporting
- Performance monitoring

### 4.5 :support:testing
- Test utilities
- Test helpers
- Mock data
- Test configurations

## 5. Modulberoenden

### 5.1 Beroendegraf
```
:app
├── :core:common
├── :core:domain
├── :core:data
├── :features:auth
├── :features:garden
├── :features:plant
├── :features:seed
├── :features:task
├── :features:weather
├── :features:notification
├── :features:settings
├── :features:search
├── :features:statistics
├── :support:ui
├── :support:network
├── :support:database
├── :support:analytics
└── :support:testing
```

### 5.2 Feature Module Beroenden
```
:features:auth
├── :core:common
├── :core:domain
└── :support:ui

:features:garden
├── :core:common
├── :core:domain
├── :core:data
└── :support:ui

:features:plant
├── :core:common
├── :core:domain
├── :core:data
├── :features:garden
└── :support:ui
```

## 6. Modulkonfiguration

### 6.1 build.gradle.kts Struktur
```kotlin
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.bps.plantseeds3.feature.xxx"
    compileSdk = 34
    
    defaultConfig {
        minSdk = 24
        targetSdk = 34
    }
    
    buildFeatures {
        compose = true
    }
    
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":support:ui"))
}
```

### 6.2 Versionshantering
- Centraliserad versionshantering i libs.versions.toml
- Konsistenta versioner över alla moduler
- Automatisk versionsuppdatering

## 7. Modulutveckling

### 7.1 Nya Features
1. Skapa ny feature-modul
2. Konfigurera build.gradle.kts
3. Implementera Clean Architecture
4. Lägg till beroenden
5. Implementera funktionalitet
6. Lägg till tester
7. Dokumentera

### 7.2 Modulunderhåll
1. Uppdatera beroenden
2. Refaktorera kod
3. Optimera prestanda
4. Uppdatera dokumentation
5. Testa kompatibilitet

## 8. Nästa steg

När modulstrukturen är klar, gå vidare till:
1. [Feature Implementation](03_Feature_Implementation.md)
2. [Testing Strategy](04_Testing_Strategy.md) 