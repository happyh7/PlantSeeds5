# Tillgänglighet i PlantSeeds

## 1. Översikt

Detta dokument beskriver hur tillgänglighet (accessibility) implementeras i PlantSeeds för att säkerställa att appen är användbar för alla användare, inklusive de med funktionsnedsättningar.

## 2. Tillgänglighetskrav

### 2.1 Skärmläsare
- Alla UI-element måste ha beskrivande text
- Logisk läsordning
- Korrekt semantisk struktur
- Alternativ text för bilder

### 2.2 Visuell tillgänglighet
- Tillräcklig kontrast mellan text och bakgrund
- Anpassningsbara textstorlekar
- Tydliga färgindikatorer
- Stöd för mörkt läge

### 2.3 Motorisk tillgänglighet
- Stöd för tangentbordsnavigering
- Tillräckligt stora tryckytor
- Anpassningsbara interaktionsavstånd
- Alternativa interaktionsmetoder

### 2.4 Kognitiv tillgänglighet
- Tydlig och enkel språkbruk
- Konsekvent design
- Förutsägbara interaktioner
- Tydlig feedback

## 3. Implementering

### 3.1 Skärmläsare
```kotlin
// Exempel på tillgänglig implementering
@Composable
fun PlantCard(
    plant: Plant,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .semantics {
                // Beskrivande text för skärmläsare
                contentDescription = "Växtkort för ${plant.name}. ${plant.description}"
                // Tydlig roll
                role = Role.Button
            }
    ) {
        Column {
            // Bild med alternativ text
            AsyncImage(
                model = plant.imageUrl,
                contentDescription = "Bild av ${plant.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            // Text med korrekt semantik
            Text(
                text = plant.name,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.semantics { heading() }
            )
            Text(
                text = plant.description,
                style = MaterialTheme.typography.body1
            )
        }
    }
}
```

### 3.2 Visuell tillgänglighet
```kotlin
// Exempel på tillgänglig färghantering
object AccessibilityColors {
    val primary = Color(0xFF1B5E20) // Mörkgrön med god kontrast
    val onPrimary = Color.White
    val background = Color.White
    val onBackground = Color.Black
    
    // Kontrastkontroll
    fun hasSufficientContrast(foreground: Color, background: Color): Boolean {
        // Implementera kontrastberäkning enligt WCAG
        return calculateContrastRatio(foreground, background) >= 4.5
    }
}

// Exempel på anpassningsbar textstorlek
@Composable
fun AccessibleText(
    text: String,
    style: TextStyle = MaterialTheme.typography.body1,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = style.copy(
            fontSize = style.fontSize * LocalAccessibilitySettings.current.textScale
        ),
        modifier = modifier
    )
}
```

### 3.3 Motorisk tillgänglighet
```kotlin
// Exempel på tillgänglig knapp
@Composable
fun AccessibleButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .minimumTouchTargetSize() // Minst 48x48dp
            .semantics {
                // Tydlig roll och beskrivning
                role = Role.Button
                contentDescription = text
            }
    ) {
        Text(text = text)
    }
}
```

### 3.4 Kognitiv tillgänglighet
```kotlin
// Exempel på tydlig feedback
@Composable
fun PlantActionButton(
    plant: Plant,
    action: PlantAction,
    onAction: () -> Unit
) {
    val feedback = remember { LocalFeedback.current }
    
    AccessibleButton(
        onClick = {
            onAction()
            // Tydlig feedback
            feedback.performHapticFeedback(HapticFeedbackType.LongPress)
        },
        text = when (action) {
            PlantAction.WATER -> "Vattna ${plant.name}"
            PlantAction.FERTILIZE -> "Gödsla ${plant.name}"
            PlantAction.HARVEST -> "Skörda ${plant.name}"
        }
    )
}
```

## 4. Testning

### 4.1 Automatiserade tester
- Kontrastkontroll
- Semantisk struktur
- Touch target storlekar
- Textstorleksanpassning

### 4.2 Manuella tester
- Skärmläsartestning
- Tangentbordsnavigering
- Färgblindhetssimulering
- Motoriska tester

### 4.3 Användartestning
- Testning med användare med funktionsnedsättningar
- Feedback-samling
- Iterativ förbättring
- Kontinuerlig validering

## 5. Bästa praxis

### 5.1 Design
- Använd Material Design 3
- Följ WCAG 2.1 riktlinjer
- Implementera systeminställningar
- Tänk på alla användargrupper

### 5.2 Utveckling
- Testa tidigt och ofta
- Dokumentera tillgänglighetsfunktioner
- Använd tillgänglighetsverktyg
- Följ Android tillgänglighetsriktlinjer

### 5.3 Underhåll
- Regelbundna tillgänglighetsgranskningar
- Uppdateringar baserade på feedback
- Kontinuerlig förbättring
- Dokumentation av ändringar

## 6. Resurser

### 6.1 Verktyg
- Android Accessibility Scanner
- TalkBack
- Switch Access
- Voice Access

### 6.2 Dokumentation
- Android Accessibility Guidelines
- WCAG 2.1
- Material Design Accessibility
- Android Accessibility API

## 7. Checklista

### 7.1 Grundläggande krav
- [ ] Alla bilder har alternativ text
- [ ] Tillräcklig kontrast
- [ ] Anpassningsbara textstorlekar
- [ ] Tydliga färgindikatorer
- [ ] Tillräckligt stora tryckytor
- [ ] Logisk navigering
- [ ] Tydlig feedback
- [ ] Stöd för systeminställningar

### 7.2 Avancerade krav
- [ ] Stöd för skärmläsare
- [ ] Tangentbordsnavigering
- [ ] Anpassningsbara interaktioner
- [ ] Tydlig språkbruk
- [ ] Konsekvent design
- [ ] Förutsägbara interaktioner
- [ ] Omfattande testning
- [ ] Kontinuerlig förbättring 