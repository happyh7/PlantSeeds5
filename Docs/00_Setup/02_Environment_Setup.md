# Miljökonfiguration

Detta dokument beskriver hur man konfigurerar utvecklingsmiljön för PlantSeeds-projektet.

## 1. Krav

### 1.1 Systemkrav
- Windows 10/11, macOS eller Linux
- Minst 8 GB RAM (16 GB rekommenderas)
- Minst 20 GB ledigt diskutrymme
- Java Development Kit (JDK) 17
- Android Studio Hedgehog | 2023.1.1 eller senare

### 1.2 Nödvändiga verktyg
1. **Android Studio**
   - Ladda ner från [developer.android.com](https://developer.android.com/studio)
   - Installera med följande komponenter:
     - Android SDK
     - Android SDK Platform
     - Android Virtual Device
     - Performance (Intel ® HAXM)
     - Android Emulator

2. **JDK 17**
   - Ladda ner från [adoptium.net](https://adoptium.net/)
   - Alternativt använd den som kommer med Android Studio

3. **Git**
   - Ladda ner från [git-scm.com](https://git-scm.com/downloads)
   - Konfigurera med ditt namn och e-post:
     ```bash
     git config --global user.name "Ditt namn"
     git config --global user.email "din.email@example.com"
     ```

## 2. Android Studio Konfiguration

### 2.1 Installera plugins
1. Öppna Android Studio
2. Gå till File > Settings > Plugins
3. Installera följande plugins:
   - Kotlin
   - Android NDK
   - Firebase Assistant
   - Git Integration
   - Markdown
   - Code With Me

### 2.2 Konfigurera SDK
1. Öppna SDK Manager (Tools > SDK Manager)
2. Installera följande:
   - Android SDK Platform 34
   - Android SDK Build-Tools 34.0.0
   - Android SDK Command-line Tools
   - Android Emulator
   - Android SDK Platform-Tools
   - Google Play services
   - Google APIs Intel x86 Atom System Image

### 2.3 Konfigurera Emulator
1. Öppna AVD Manager (Tools > Device Manager)
2. Skapa ny virtuell enhet:
   - Device: Pixel 6
   - System Image: API 34
   - RAM: 2048 MB
   - VM heap: 512 MB
   - Internal Storage: 2048 MB
   - SD Card: 512 MB

## 3. Firebase Konfiguration

### 3.1 Skapa Firebase-projekt
1. Gå till [Firebase Console](https://console.firebase.google.com/)
2. Skapa nytt projekt: PlantSeeds
3. Aktivera följande tjänster:
   - Authentication
   - Firestore Database
   - Storage
   - Analytics

### 3.2 Konfigurera Android-app
1. I Firebase Console, lägg till Android-app
2. Package name: com.bps.plantseeds3
3. Ladda ner google-services.json
4. Placera filen i app/-mappen

## 4. Git Konfiguration

### 4.1 Repository Setup
```bash
# Klona repository
git clone https://github.com/happyh7/PlantSeeds3.git

# Skapa och byt till feature-gren
git checkout -b feature/initial-setup

# Konfigurera remote
git remote add origin https://github.com/happyh7/PlantSeeds3.git
```

### 4.2 Git Hooks
Skapa .git/hooks/pre-commit med följande innehåll:
```bash
#!/bin/sh
./gradlew ktlintCheck
```

## 5. Verifiering

### 5.1 Kontrollera installation
```bash
# Kontrollera Java-version
java -version

# Kontrollera Android SDK
adb version

# Kontrollera Git
git --version
```

### 5.2 Testa byggmiljö
1. Öppna projektet i Android Studio
2. Vänta på att Gradle ska synka
3. Kör "Clean Project"
4. Kör "Rebuild Project"
5. Verifiera att inga fel uppstår

## 6. Felsökning

### 6.1 Vanliga problem
1. **Gradle-synkroniseringsfel**
   - Rensa Gradle-cache
   - Uppdatera Gradle-version
   - Kontrollera internetanslutning

2. **Emulator-problem**
   - Kontrollera HAXM-installation
   - Öka RAM-tilldelning
   - Uppdatera GPU-drivrutiner

3. **Firebase-problem**
   - Verifiera google-services.json
   - Kontrollera SHA-1-certifikat
   - Verifiera Firebase-regler

### 6.2 Användbara kommandon
```bash
# Rensa projekt
./gradlew clean

# Bygg projekt
./gradlew build

# Kör tester
./gradlew test

# Kontrollera kodstil
./gradlew ktlintCheck
```

## 7. Nästa steg

När miljökonfigurationen är klar, gå vidare till:
1. [Dependencies](03_Dependencies.md)
2. [Clean Architecture](01_Architecture/01_Clean_Architecture.md) 