# Changelog

## Instruktioner för Dokumentation av Ändringar

Alla ändringar i projektet ska dokumenteras enligt följande format:

1. **Datumformat**: [ÅÅÅÅ-MM-DD]
2. **Tidsformat**: HH:MM (24-timmarsformat)
3. **Kategorisering**: Varje ändring ska ha en tydlig kategori (t.ex. "Databasstruktur", "UI", "Bugfix")
4. **Detaljnivå**: Beskriv ändringen så specifikt som möjligt
5. **Kodreferenser**: Om relevant, inkludera referenser till specifika filer eller commits

Exempel på korrekt format:
```
## [2024-03-20] - Databasstruktur
- 14:30: Uppdaterad databasstruktur med nya fält för fröhantering
- 15:45: Lagt till metadata för fröer
- 16:30: Implementerat nya TypeConverters för komplexa datatyper
```

## Historik

## [2024-04-12] - Roadmap och Dokumentation
- 15:30: Uppdaterat roadmap med ny arkitektur- och dokumentationsfas
- 15:45: Lagt till planer för modulstrukturförbättringar
- 16:00: Uppdaterat testnings- och lanseringsfaser
- 16:15: Förbättrat tidsplanering och logisk progression

## [2024-04-12] - Databasstruktur och Säkerhetskopiering
- 14:30: Implementerat PlantEntity och PlantDao för växthantering
- 14:45: Lagt till AutoBackupService för automatisk säkerhetskopiering av databasen
- 15:00: Fixat paketstruktur för AutoBackupService i manifestet
- 15:15: Implementerat null-safety i AutoBackupService

## [2024-04-11] - Sökfunktion och Optimeringar
- 10:30: Implementerat sökfunktion i SeedsScreen med debounce för bättre prestanda
- 10:45: Skapat common-modul för delade komponenter
- 11:00: Flyttat getMonthName till MonthUtils för att undvika kodduplicering
- 11:15: Optimerat listrendering med unika nycklar i LazyColumn
- 11:30: Implementerat DebounceUtils för hantering av sökning och andra användarinteraktioner