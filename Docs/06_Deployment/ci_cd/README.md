# CI/CD Pipeline

Här dokumenteras den kontinuerliga integrationen och leveransen av applikationen.

## Innehåll
- [GitHub Actions](GitHubActions.md) - Konfiguration av GitHub Actions
- [Build Process](BuildProcess.md) - Byggprocess
- [Automated Testing](AutomatedTesting.md) - Automatiserad testning
- [Deployment](Deployment.md) - Automatiserad distribution

## Exempel på CI/CD Pipeline
```yaml
name: Android CI

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v2
    
    - name: Set up JDK 17
      uses: actions/setup-java@v2
      with:
        java-version: '17'
        distribution: 'temurin'
        
    - name: Build with Gradle
      run: ./gradlew build
      
    - name: Run Tests
      run: ./gradlew test
      
    - name: Build Release
      if: github.ref == 'refs/heads/main'
      run: ./gradlew assembleRelease
      
    - name: Upload Release
      if: github.ref == 'refs/heads/main'
      uses: actions/upload-artifact@v2
      with:
        name: app-release
        path: app/build/outputs/apk/release/
```

## Översikt
CI/CD-pipelinen automatiserar byggnad, testning och distribution av applikationen. 