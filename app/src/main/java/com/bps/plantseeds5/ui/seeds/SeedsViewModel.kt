package com.bps.plantseeds5.ui.seeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bps.plantseeds5.data.database.Seed
import com.bps.plantseeds5.data.database.SeedDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SeedsViewModel @Inject constructor(
    private val seedDao: SeedDao
) : ViewModel() {

    val seeds: StateFlow<List<Seed>> = seedDao.getAllSeeds()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
} 