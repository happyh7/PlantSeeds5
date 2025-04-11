package com.bps.plantseeds5.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

@Composable
fun <T> rememberDebouncedState(
    initialValue: T,
    delayMillis: Long = 300L,
    onChange: (T) -> Unit
): T {
    var state by remember { mutableStateOf(initialValue) }
    var debouncedValue by remember { mutableStateOf(initialValue) }

    LaunchedEffect(state) {
        delay(delayMillis)
        debouncedValue = state
        onChange(debouncedValue)
    }

    return state
} 