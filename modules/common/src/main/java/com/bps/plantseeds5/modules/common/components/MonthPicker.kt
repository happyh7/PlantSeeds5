package com.bps.plantseeds5.modules.common.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MonthPicker(
    value: Int,
    onValueChange: (Int) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        
        Slider(
            value = value.toFloat(),
            onValueChange = { onValueChange(it.toInt()) },
            valueRange = 1f..12f,
            steps = 11,
            modifier = Modifier.fillMaxWidth()
        )
        
        Text(
            text = when (value) {
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
                else -> ""
            },
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
} 