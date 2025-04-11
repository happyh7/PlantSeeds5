package com.bps.plantseeds5.ui.seeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bps.plantseeds5.data.database.Seed
import com.bps.plantseeds5.data.database.SeedDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeedsViewModel @Inject constructor(
    private val seedDao: SeedDao
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val seeds: Flow<List<Seed>> = combine(
        seedDao.getAllSeeds(),
        searchQuery
    ) { seeds, query ->
        if (query.isBlank()) {
            seeds
        } else {
            seeds.filter { seed ->
                seed.name.contains(query, ignoreCase = true) ||
                seed.description.contains(query, ignoreCase = true) ||
                seed.variety?.contains(query, ignoreCase = true) == true
            }
        }
    }

    fun insertSeed(
        name: String,
        description: String,
        sowingTime: Int,
        harvestTime: Int,
        variety: String? = null,
        difficultyLevel: Int = 1
    ) {
        viewModelScope.launch {
            seedDao.insertSeed(
                name = name,
                description = description,
                sowingTime = sowingTime,
                harvestTime = harvestTime,
                variety = variety,
                difficultyLevel = difficultyLevel
            )
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
} 