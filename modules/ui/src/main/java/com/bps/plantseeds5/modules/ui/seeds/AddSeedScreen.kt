package com.bps.plantseeds5.modules.ui.seeds

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
import com.bps.plantseeds5.modules.common.components.MonthPicker
import com.bps.plantseeds5.modules.domain.model.Seed
import com.bps.plantseeds5.modules.domain.exception.ValidationException

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

            MonthPicker(
                value = sowingTime,
                onValueChange = { sowingTime = it },
                label = "Såtid (månad)"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MonthPicker(
                value = harvestTime,
                onValueChange = { harvestTime = it },
                label = "Skördetid (månad)"
            )

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
                        viewModel.insertSeed(
                            Seed(
                                name = name,
                                description = description,
                                plantingMonths = listOf(sowingTime, harvestTime)
                            )
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