package com.compose.filerush.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AudioFile
import androidx.compose.material.icons.outlined.DevicesOther
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.VideoFile
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.filerush.ui.theme.FilerushTheme
import com.compose.filerush.ui.theme.brightBlue
import com.compose.filerush.ui.theme.green
import com.compose.filerush.ui.theme.orange

@Composable
fun FileStorage(
    modifier: Modifier = Modifier
) {

    val storageCategories = listOf(
        BarChartInput(13,"Video", MaterialTheme.colorScheme.onPrimaryContainer, Icons.Outlined.VideoFile),
        BarChartInput(9,"Audio", MaterialTheme.colorScheme.onSecondaryContainer, Icons.Outlined.AudioFile),
        BarChartInput(7,"Images", MaterialTheme.colorScheme.onTertiaryContainer, Icons.Outlined.Image),
        BarChartInput(4,"Other", MaterialTheme.colorScheme.onSurfaceVariant, Icons.Outlined.DevicesOther),
    )

    val sum = storageCategories.sumOf { it.value }

    val freeSpace = 48 - sum

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BarChart(
            inputList = storageCategories,
            showDescription = false,
            height = 500.dp,
            modifier = Modifier
                .weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            storageCategories.forEach { barChartInput ->
                StorageItem(
                    barChartInput = barChartInput,
                    memory = "${barChartInput.value} GB",
                    modifier = Modifier
                        .padding(5.dp)
                )
            }

            Box(
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.onSecondary),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "$freeSpace GB",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Free Space",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
fun StorageItem(
    barChartInput: BarChartInput,
    modifier: Modifier = Modifier,
    memory: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier,
            tint = barChartInput.color,
            imageVector = barChartInput.icon,
            contentDescription = barChartInput.description
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            modifier = Modifier
                .weight(1f),
            text = barChartInput.description,
            style = MaterialTheme.typography.bodySmall
        )

        Box {
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 2.dp),
                text = memory,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(barChartInput.color)
                    .align(AbsoluteAlignment.TopRight)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FileStoragePreview() {
    FilerushTheme {
        Surface {
            FileStorage()
        }
    }
}