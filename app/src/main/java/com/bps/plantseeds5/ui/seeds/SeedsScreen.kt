package com.bps.plantseeds5.ui.seeds

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bps.plantseeds5.data.database.Seed

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
                title = { Text("Frön") },
                actions = {
                    IconButton(onClick = onAddSeed) {
                        Icon(Icons.Default.Add, contentDescription = "Lägg till frö")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddSeed) {
                Icon(Icons.Default.Add, contentDescription = "Lägg till frö")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Sökfält
            OutlinedTextField(
                value = searchQuery,
                onValueChange = viewModel::onSearchQueryChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Sök frön...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Sök") },
                singleLine = true
            )

            // Frölista
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
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