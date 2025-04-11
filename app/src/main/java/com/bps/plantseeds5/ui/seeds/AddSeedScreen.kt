package com.bps.plantseeds5.ui.seeds

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
    var difficultyLevel by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lägg till frö") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Tillbaka")
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

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Såtid")
                    Slider(
                        value = sowingTime.toFloat(),
                        onValueChange = { sowingTime = it.toInt() },
                        valueRange = 1f..12f,
                        steps = 11
                    )
                    Text(getMonthName(sowingTime))
                }

                Column {
                    Text("Skördetid")
                    Slider(
                        value = harvestTime.toFloat(),
                        onValueChange = { harvestTime = it.toInt() },
                        valueRange = 1f..12f,
                        steps = 11
                    )
                    Text(getMonthName(harvestTime))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Svårighetsgrad")
            Slider(
                value = difficultyLevel.toFloat(),
                onValueChange = { difficultyLevel = it.toInt() },
                valueRange = 1f..5f,
                steps = 4
            )
            Text("$difficultyLevel av 5")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.insertSeed(
                        name = name,
                        description = description,
                        sowingTime = sowingTime,
                        harvestTime = harvestTime,
                        variety = variety.takeIf { it.isNotBlank() },
                        difficultyLevel = difficultyLevel
                    )
                    onNavigateBack()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = name.isNotBlank() && description.isNotBlank()
            ) {
                Text("Spara")
            }
        }
    }
}

private fun getMonthName(month: Int): String = when (month) {
    1 -> "Januari"
    2 -> "Februari"
    3 -> "Mars"
    4 -> "April"
    5 -> "Maj"
    6 -> "Juni"
    7 -> "Juli"
    8 -> "Augusti"
    9 -> "September"
    10 -> "Oktober"
    11 -> "November"
    12 -> "December"
    else -> "Okänd"
} 