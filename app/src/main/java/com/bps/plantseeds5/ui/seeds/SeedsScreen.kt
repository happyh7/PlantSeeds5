package com.bps.plantseeds5.ui.seeds

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bps.plantseeds5.data.Seed
import com.bps.plantseeds5.common.MonthUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeedsScreen(
    onSeedClick: (Seed) -> Unit,
    onAddSeed: () -> Unit,
    viewModel: SeedsViewModel = hiltViewModel()
) {
    val seeds by viewModel.seeds.collectAsState(initial = emptyList())
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mina frön") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddSeed
            ) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.onSearchQueryChanged(it) },
                label = { Text("Sök") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(seeds) { seed ->
                    SeedItem(
                        seed = seed,
                        onSeedClick = { onSeedClick(seed) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeedItem(
    seed: Seed,
    onSeedClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = onSeedClick
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = seed.name,
                style = MaterialTheme.typography.titleMedium
            )
            if (seed.variety.isNotBlank()) {
                Text(
                    text = seed.variety,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                text = "Såtid: ${MonthUtils.getMonthName(seed.sowingTime)}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Skördetid: ${MonthUtils.getMonthName(seed.harvestTime)}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Svårighetsgrad: ${seed.difficulty} av 5",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
} 