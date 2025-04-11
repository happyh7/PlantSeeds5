package com.bps.plantseeds5.ui.seeds

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bps.plantseeds5.data.database.Seed

@Composable
fun SeedItem(
    seed: Seed,
    onSeedClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable(onClick = onSeedClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = seed.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            seed.variety?.let { variety ->
                Text(
                    text = variety,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Såtid: ${getMonthName(seed.sowingTime)}",
                    style = MaterialTheme.typography.bodyMedium
                )
                
                Text(
                    text = "Skörd: ${getMonthName(seed.harvestTime)}",
                    style = MaterialTheme.typography.bodyMedium
                )
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