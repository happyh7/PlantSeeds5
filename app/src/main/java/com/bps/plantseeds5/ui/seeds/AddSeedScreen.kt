package com.bps.plantseeds5.ui.seeds

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bps.plantseeds5.common.MonthUtils
import com.bps.plantseeds5.data.database.ValidationException
import com.bps.plantseeds5.data.database.Seed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSeedScreen(
    onNavigateBack: () -> Unit,
    viewModel: SeedsViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var variety by remember { mutableStateOf("") }
    var sowingTime by remember { mutableStateOf(1) }
    var harvestTime by remember { mutableStateOf(1) }
    var difficulty by remember { mutableStateOf(1) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lägg till frö") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Text("←")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Namn") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Beskrivning") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = variety,
                onValueChange = { variety = it },
                label = { Text("Sort") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Såtid (månad)")
            Slider(
                value = sowingTime.toFloat(),
                onValueChange = { sowingTime = it.toInt() },
                valueRange = 1f..12f,
                steps = 10,
                modifier = Modifier.fillMaxWidth()
            )
            Text("Månad: $sowingTime")

            Spacer(modifier = Modifier.height(16.dp))

            Text("Skördetid (månad)")
            Slider(
                value = harvestTime.toFloat(),
                onValueChange = { harvestTime = it.toInt() },
                valueRange = 1f..12f,
                steps = 10,
                modifier = Modifier.fillMaxWidth()
            )
            Text("Månad: $harvestTime")

            Spacer(modifier = Modifier.height(16.dp))

            Text("Svårighetsgrad")
            Slider(
                value = difficulty.toFloat(),
                onValueChange = { difficulty = it.toInt() },
                valueRange = 1f..5f,
                steps = 3,
                modifier = Modifier.fillMaxWidth()
            )
            Text("Nivå: $difficulty")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    try {
                        viewModel.addSeed(
                            name = name,
                            description = description,
                            variety = variety,
                            sowingTime = sowingTime,
                            harvestTime = harvestTime,
                            difficulty = difficulty
                        )
                        onNavigateBack()
                    } catch (e: ValidationException) {
                        errorMessage = e.message
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = name.isNotBlank() && description.isNotBlank()
            ) {
                Text("Spara")
            }
        }
    }
} 