package com.bps.plantseeds5.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bps.plantseeds5.data.database.validation.SeedValidator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

@Dao
abstract class SeedDao {
    private val validator = SeedValidator()

    @Query("SELECT * FROM seeds ORDER BY name ASC")
    abstract fun getAllSeeds(): Flow<List<Seed>>

    @Query("SELECT * FROM seeds WHERE id = :id")
    abstract suspend fun getSeedById(id: Long): Seed?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    protected abstract suspend fun insertSeedInternal(seed: Seed): Long

    @Update
    protected abstract suspend fun updateSeedInternal(seed: Seed)

    @Delete
    abstract suspend fun deleteSeed(seed: Seed)

    suspend fun insertSeed(
        name: String,
        description: String,
        sowingTime: Int,
        harvestTime: Int,
        variety: String? = null,
        difficultyLevel: Int = 1
    ): Long {
        val seed = Seed(
            name = name.trim(),
            description = description.trim(),
            sowingTime = sowingTime,
            harvestTime = harvestTime,
            variety = variety?.trim(),
            difficultyLevel = difficultyLevel,
            createdAt = Date()
        )
        
        when (val result = validator.validate(seed)) {
            is SeedValidator.ValidationResult.Valid -> return insertSeedInternal(seed)
            is SeedValidator.ValidationResult.Invalid -> {
                val errorMessages = result.errors.map { validator.getErrorMessage(it) }
                throw ValidationException(errorMessages)
            }
        }
    }

    suspend fun updateSeed(
        id: Long,
        name: String,
        description: String,
        sowingTime: Int,
        harvestTime: Int,
        variety: String? = null,
        difficultyLevel: Int = 1
    ) {
        val existingSeed = getSeedById(id) ?: throw IllegalArgumentException("Seed with id $id not found")
        
        val updatedSeed = existingSeed.copy(
            name = name.trim(),
            description = description.trim(),
            sowingTime = sowingTime,
            harvestTime = harvestTime,
            variety = variety?.trim(),
            difficultyLevel = difficultyLevel
        )

        when (val result = validator.validate(updatedSeed)) {
            is SeedValidator.ValidationResult.Valid -> updateSeedInternal(updatedSeed)
            is SeedValidator.ValidationResult.Invalid -> {
                val errorMessages = result.errors.map { validator.getErrorMessage(it) }
                throw ValidationException(errorMessages)
            }
        }
    }
}

class ValidationException(
    val errors: List<String>
) : Exception("Validation failed: ${errors.joinToString(", ")}") 