package com.bps.plantseeds5.ui.seeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bps.plantseeds5.data.Seed
import com.bps.plantseeds5.data.SeedDao
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

    val seeds = combine(
        seedDao.getAllSeeds(),
        searchQuery
    ) { seeds, query ->
        if (query.isBlank()) {
            seeds
        } else {
            seeds.filter { seed ->
                seed.name.contains(query, ignoreCase = true) ||
                seed.description.contains(query, ignoreCase = true) ||
                seed.variety.contains(query, ignoreCase = true)
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun addSeed(
        name: String,
        description: String,
        variety: String,
        sowingTime: Int,
        harvestTime: Int,
        difficulty: Int
    ) {
        viewModelScope.launch {
            val seed = Seed(
                name = name,
                description = description,
                variety = variety,
                sowingTime = sowingTime,
                harvestTime = harvestTime,
                difficulty = difficulty
            )
            seedDao.insert(seed)
        }
    }
} 