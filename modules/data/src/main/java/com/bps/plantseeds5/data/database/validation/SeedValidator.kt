package com.bps.plantseeds5.data.database.validation

import com.bps.plantseeds5.data.database.Seed
import java.util.Date

class SeedValidator {
    sealed class ValidationResult {
        object Valid : ValidationResult()
        data class Invalid(val errors: List<ValidationError>) : ValidationResult()
    }

    sealed class ValidationError {
        object EmptyName : ValidationError()
        object EmptyDescription : ValidationError()
        object InvalidSowingTime : ValidationError()
        object InvalidHarvestTime : ValidationError()
        object InvalidHarvestBeforeSowing : ValidationError()
        object InvalidDifficultyLevel : ValidationError()
        object InvalidVarietyLength : ValidationError()
        object FutureCreationDate : ValidationError()
    }

    fun validate(seed: Seed): ValidationResult {
        val errors = mutableListOf<ValidationError>()

        // Validera namn
        if (seed.name.isBlank()) {
            errors.add(ValidationError.EmptyName)
        }

        // Validera beskrivning
        if (seed.description.isBlank()) {
            errors.add(ValidationError.EmptyDescription)
        }

        // Validera såtid (1-12 för månader)
        if (seed.sowingTime !in 1..12) {
            errors.add(ValidationError.InvalidSowingTime)
        }

        // Validera skördetid (1-12 för månader)
        if (seed.harvestTime !in 1..12) {
            errors.add(ValidationError.InvalidHarvestTime)
        }

        // Validera att skördetid inte är före såtid
        // Om såtiden är i slutet av året och skördetiden i början av nästa år är det OK
        if (seed.harvestTime < seed.sowingTime && 
            !(seed.sowingTime in 10..12 && seed.harvestTime in 1..3)) {
            errors.add(ValidationError.InvalidHarvestBeforeSowing)
        }

        // Validera svårighetsgrad (1-5)
        if (seed.difficultyLevel !in 1..5) {
            errors.add(ValidationError.InvalidDifficultyLevel)
        }

        seed.variety?.let { variety ->
            if (variety.isNotBlank() && variety.length > 100) {
                errors.add(ValidationError.InvalidVarietyLength)
            }
        }

        // Validera skapandedatum (inte i framtiden)
        if (seed.createdAt.after(Date())) {
            errors.add(ValidationError.FutureCreationDate)
        }

        return if (errors.isEmpty()) {
            ValidationResult.Valid
        } else {
            ValidationResult.Invalid(errors)
        }
    }

    fun getErrorMessage(error: ValidationError): String {
        return when (error) {
            ValidationError.EmptyName -> "Name cannot be empty"
            ValidationError.EmptyDescription -> "Description cannot be empty"
            ValidationError.InvalidSowingTime -> "Sowing time must be between 1 and 12"
            ValidationError.InvalidHarvestTime -> "Harvest time must be between 1 and 12"
            ValidationError.InvalidHarvestBeforeSowing -> "Harvest time cannot be before sowing time"
            ValidationError.InvalidDifficultyLevel -> "Difficulty level must be between 1 and 5"
            ValidationError.InvalidVarietyLength -> "Variety name cannot be longer than 100 characters"
            ValidationError.FutureCreationDate -> "Creation date cannot be in the future"
        }
    }
} 