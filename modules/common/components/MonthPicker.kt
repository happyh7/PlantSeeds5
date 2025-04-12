package com.bps.plantseeds5.modules.common.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bps.plantseeds5.modules.common.MonthUtils

@Composable
fun MonthPicker(
    value: Int,
    onValueChange: (Int) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(label)
        Slider(
            value = value.toFloat(),
            onValueChange = { onValueChange(it.toInt()) },
            valueRange = 1f..12f,
            steps = 10,
            modifier = Modifier.fillMaxWidth()
        )
        Text("Månad: ${MonthUtils.getMonthName(value)}")
    }
} 