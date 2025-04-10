# Teststrategi

Detta dokument beskriver teststrategin för PlantSeeds-projektet.

## 1. Testnivåer

### 1.1 Unit Tests
- Testar enskilda komponenter
- Snabba att köra
- Isolerade från externa beroenden
- Hög täckningsgrad

### 1.2 Integration Tests
- Testar interaktion mellan komponenter
- Verifierar dataflöde
- Testar repository implementations
- Testar API-integration

### 1.3 UI Tests
- Testar användargränssnitt
- Verifierar användarflöden
- Testar responsivitet
- Testar tillgänglighet

## 2. Testverktyg

### 2.1 Unit Testing
```kotlin
// Exempel på Unit Test
@Test
fun `getPlants returns success when plants exist`() = runTest {
    // Given
    val plants = listOf(
        PlantEntity(id = "1", name = "Plant 1"),
        PlantEntity(id = "2", name = "Plant 2")
    )
    whenever(plantDao.getAll()).thenReturn(plants)
    
    // When
    val result = plantRepository.getPlants().first()
    
    // Then
    assertThat(result).isInstanceOf(Resource.Success::class.java)
    assertThat((result as Resource.Success).data).hasSize(2)
}
```

### 2.2 Integration Testing
```kotlin
// Exempel på Integration Test
@Test
fun `plant repository syncs with remote data`() = runTest {
    // Given
    val localPlants = listOf(PlantEntity(id = "1", name = "Local Plant"))
    val remotePlants = listOf(PlantEntity(id = "2", name = "Remote Plant"))
    
    // When
    plantRepository.syncPlants()
    
    // Then
    verify(plantDao).savePlants(remotePlants)
    verify(plantDao).getAll()
}
```

### 2.3 UI Testing
```kotlin
// Exempel på UI Test
@Test
fun `plant list displays plants correctly`() {
    // Given
    val plants = listOf(
        Plant(id = "1", name = "Plant 1"),
        Plant(id = "2", name = "Plant 2")
    )
    
    // When
    composeTestRule.setContent {
        PlantListScreen(plants = plants)
    }
    
    // Then
    composeTestRule.onNodeWithText("Plant 1").assertExists()
    composeTestRule.onNodeWithText("Plant 2").assertExists()
}
```

## 3. Teststruktur

### 3.1 Test Directory Structure
```
src/test/java/com/bps/plantseeds3
├── data
│   ├── repository
│   ├── datasource
│   └── mapper
├── domain
│   ├── usecase
│   └── model
└── presentation
    ├── viewmodel
    ├── screen
    └── component
```

### 3.2 Test Naming Convention
- `GivenWhenThen` format
- Beskrivande testnamn
- Inkludera förväntat resultat
- Använd backticks för läsbarhet

### 3.3 Test Data
```kotlin
// Test Data Factory
object TestData {
    fun createPlant(
        id: String = UUID.randomUUID().toString(),
        name: String = "Test Plant",
        description: String = "Test Description"
    ) = Plant(
        id = id,
        name = name,
        description = description
    )
    
    fun createPlantEntity(
        id: String = UUID.randomUUID().toString(),
        name: String = "Test Plant",
        description: String = "Test Description"
    ) = PlantEntity(
        id = id,
        name = name,
        description = description
    )
}
```

## 4. Test Coverage

### 4.1 Coverage Krav
- Minst 80% kodtäckning
- 100% för kritiska komponenter
- Testa alla edge cases
- Testa felhantering

### 4.2 Coverage Tools
- JaCoCo för coverage rapporter
- SonarQube för kvalitetsanalys
- GitHub Actions för CI/CD
- Automatiserade rapporter

## 5. Test Automation

### 5.1 CI/CD Pipeline
```yaml
# GitHub Actions Workflow
name: Test

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Run Unit Tests
        run: ./gradlew test
      - name: Run UI Tests
        run: ./gradlew connectedAndroidTest
      - name: Generate Coverage Report
        run: ./gradlew jacocoTestReport
```

### 5.2 Test Reports
- HTML-rapporter
- XML-rapporter för CI
- Trendanalys
- Historiska data

## 5. A/B-testning

### 5.1 Översikt
A/B-testning är en viktig del av vår teststrategi för att optimera användarupplevelsen. Se separat dokument [10_AB_Testing.md](10_AB_Testing.md) för detaljerad information om A/B-testning.

### 5.2 Integration med Övrig Testning
- A/B-tester körs parallellt med andra tester
- Resultat från A/B-tester påverkar QA-processen
- Feedback från A/B-tester används för att förbättra testkvaliteten

### 5.3 Testcykel
1. Planera A/B-test
2. Implementera testvariabler
3. Kör test parallellt med QA
4. Analysera resultat
5. Implementera vinnande variant
6. Uppdatera testdokumentation

## 6. Test Best Practices

### 6.1 Test Design
- AAA (Arrange-Act-Assert) pattern
- One assertion per test
- Isolera tester
- Använd mocks och stubs

### 6.2 Performance
- Snabba tester
- Parallell körning
- Caching av testdata
- Optimera CI/CD

### 6.3 Maintenance
- Uppdatera tester med kod
- Dokumentera tester
- Review testkod
- Refaktorera tester

## 7. Test Checklista

### 7.1 Unit Tests
- [ ] Testa alla public metoder
- [ ] Testa edge cases
- [ ] Testa felhantering
- [ ] Använd mocks

### 7.2 Integration Tests
- [ ] Testa repository implementations
- [ ] Testa API-integration
- [ ] Testa databasoperationer
- [ ] Testa cache

### 7.3 UI Tests
- [ ] Testa alla skärmar
- [ ] Testa navigation
- [ ] Testa användarinteraktion
- [ ] Testa tillgänglighet

## 8. Nästa steg

När teststrategin är implementerad, gå vidare till:
1. [Deployment Process](05_Deployment_Process.md)
2. [Monitoring and Analytics](06_Monitoring_and_Analytics.md) 