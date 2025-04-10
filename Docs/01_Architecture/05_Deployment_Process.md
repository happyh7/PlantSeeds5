# Deployment Process

Detta dokument beskriver deployment-processen för PlantSeeds-projektet.

## 1. Release Management

### 1.1 Versionering
- Semantic Versioning (MAJOR.MINOR.PATCH)
- Versionshantering i build.gradle.kts
- Versionshantering i Firebase Console
- Versionshantering i Google Play Console

### 1.2 Release Notes
- Beskriv nya features
- Lista bugfixar
- Dokumentera breaking changes
- Uppdatera changelog

## 2. Build Process

### 2.1 Build Konfiguration
```kotlin
android {
    defaultConfig {
        versionCode = 1
        versionName = "1.0.0"
    }
    
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    
    signingConfigs {
        create("release") {
            storeFile = file("keystore.jks")
            storePassword = System.getenv("KEYSTORE_PASSWORD")
            keyAlias = System.getenv("KEY_ALIAS")
            keyPassword = System.getenv("KEY_PASSWORD")
        }
    }
}
```

### 2.2 Build Pipeline
```yaml
# GitHub Actions Workflow
name: Build and Deploy

on:
  push:
    tags:
      - 'v*'

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      
      - name: Set up JDK
        uses: actions/setup-java@v2
        with:
          java-version: '17'
          
      - name: Build Release APK
        run: ./gradlew assembleRelease
        env:
          KEYSTORE_PASSWORD: ${{ secrets.KEYSTORE_PASSWORD }}
          KEY_ALIAS: ${{ secrets.KEY_ALIAS }}
          KEY_PASSWORD: ${{ secrets.KEY_PASSWORD }}
          
      - name: Upload APK
        uses: actions/upload-artifact@v2
        with:
          name: app-release
          path: app/build/outputs/apk/release/app-release.apk
```

## 3. Quality Assurance

### 3.1 Pre-release Checklista
- [ ] Alla tester passerar
- [ ] Kodtäckning uppfyller krav
- [ ] Ingen kända buggar
- [ ] Prestanda är godkänd
- [ ] Säkerhetstester är godkända
- [ ] Tillgänglighetstester är godkända

### 3.2 Code Review
- Peer review
- Automatiserade verktyg
- Säkerhetsgenomgång
- Arkitekturgenomgång

## 4. Distribution

### 4.1 Internal Testing
- Firebase App Distribution
- TestFlight (iOS)
- Internal testers
- Feedback collection

### 4.2 Beta Testing
- Google Play Beta
- Firebase Beta
- External testers
- Crash reporting

### 4.3 Production Release
- Google Play Store
- App Store (iOS)
- Staged rollout
- Monitoring

## 5. Monitoring

### 5.1 Crash Reporting
```kotlin
// Firebase Crashlytics
FirebaseCrashlytics.getInstance().setCustomKey("user_id", userId)
FirebaseCrashlytics.getInstance().recordException(e)
```

### 5.2 Analytics
```kotlin
// Firebase Analytics
FirebaseAnalytics.getInstance(this).logEvent(
    FirebaseAnalytics.Event.SELECT_ITEM,
    Bundle().apply {
        putString(FirebaseAnalytics.Param.ITEM_ID, itemId)
        putString(FirebaseAnalytics.Param.ITEM_NAME, itemName)
    }
)
```

### 5.3 Performance Monitoring
```kotlin
// Firebase Performance
val trace = FirebasePerformance.getInstance().newTrace("screen_load")
trace.start()
// ... screen loading code ...
trace.stop()
```

## 6. Post-release

### 6.1 Monitoring
- Crash rates
- ANR rates
- User feedback
- Performance metrics

### 6.2 Hotfixes
- Emergency fixes
- Critical updates
- Security patches
- Compliance updates

### 6.3 Rollback Plan
1. Identifiera problem
2. Förbered fix
3. Testa fix
4. Distribuera fix
5. Verifiera lösning

## 7. Documentation

### 7.1 Release Notes
```markdown
# PlantSeeds v1.0.0

## New Features
- Plant management
- Garden tracking
- Weather integration
- Task scheduling

## Bug Fixes
- Fixed crash on plant deletion
- Fixed sync issues
- Fixed UI glitches

## Improvements
- Better performance
- Enhanced UI
- Improved stability
```

### 7.2 API Documentation
- Swagger/OpenAPI
- Endpoint documentation
- Request/response examples
- Error codes

## 8. Nästa steg

När deployment-processen är klar, gå vidare till:
1. [Monitoring and Analytics](06_Monitoring_and_Analytics.md)
2. [Maintenance and Support](07_Maintenance_and_Support.md) 