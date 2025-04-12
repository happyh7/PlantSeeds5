package com.bps.plantseeds5.modules.ui.plants

import androidx.lifecycle.ViewModel
import com.bps.plantseeds5.modules.domain.repository.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlantsViewModel @Inject constructor(
    private val repository: PlantRepository
) : ViewModel() {
    val plants = repository.getAllPlants()
} 