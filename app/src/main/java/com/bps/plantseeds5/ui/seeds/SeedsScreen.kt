package com.bps.plantseeds5.ui.seeds

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bps.plantseeds5.data.database.Seed

@Composable
fun SeedsScreen(
    onSeedClick: (Seed) -> Unit,
    onAddSeed: () -> Unit,
    viewModel: SeedsViewModel = hiltViewModel()
) {
    val seeds by viewModel.seeds.collectAsState(initial = emptyList())

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (seeds.isEmpty()) {
            Text(
                text = "Inga frön tillagda än",
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(seeds) { seed ->
                    SeedItem(
                        seed = seed,
                        onSeedClick = { onSeedClick(seed) }
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = onAddSeed,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Lägg till frö"
            )
        }
    }
} 