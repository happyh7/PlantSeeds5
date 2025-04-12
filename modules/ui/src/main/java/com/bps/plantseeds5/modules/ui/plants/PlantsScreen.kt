package com.bps.plantseeds5.modules.ui.plants

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bps.plantseeds5.modules.domain.model.Plant

@Composable
fun PlantsScreen(
    plants: List<Plant>,
    onAddPlant: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Mina plantor",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (plants.isEmpty()) {
            Text(
                text = "Inga plantor tillagda än",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        } else {
            // TODO: Add list of plants
        }

        FloatingActionButton(
            onClick = onAddPlant,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.End)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Lägg till planta"
            )
        }
    }
} 