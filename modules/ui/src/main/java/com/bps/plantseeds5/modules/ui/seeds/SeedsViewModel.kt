package com.bps.plantseeds5.modules.ui.seeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bps.plantseeds5.modules.domain.model.Seed
import com.bps.plantseeds5.modules.domain.repository.SeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SeedsViewModel @Inject constructor(
    private val repository: SeedRepository
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val seeds: StateFlow<List<Seed>> = searchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) {
                repository.getAllSeeds()
            } else {
                repository.searchSeeds(query)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun insertSeed(seed: Seed) {
        viewModelScope.launch {
            repository.insertSeed(seed)
        }
    }
} 