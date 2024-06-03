package com.compose.filerush.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.compose.filerush.ui.theme.FilerushTheme
import com.compose.filerush.ui.theme.brightBlue
import com.compose.filerush.ui.theme.gray
import com.compose.filerush.ui.theme.green
import com.compose.filerush.ui.theme.orange
import kotlin.math.roundToInt

@Composable
fun BarChart(
    inputList: List<BarChartInput>,
    modifier: Modifier = Modifier,
    showDescription: Boolean,
    height: Dp
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {

        val listSum by remember {
            mutableIntStateOf(inputList.sumOf { it.value })
        }

        inputList.forEach { barChartInput ->
            val percentage = barChartInput.value/listSum.toFloat()

            Bar(
                modifier = Modifier
                    .height(height * percentage)
                    .weight(1f),
                primaryColor = barChartInput.color,
                percentage = percentage,
                description = barChartInput.description,
                showDescription = showDescription
            )
        }


    }
}

@Composable
fun Bar(
    modifier: Modifier = Modifier,
    primaryColor: Color,
    percentage: Float,
    description: String,
    showDescription: Boolean
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {

        val labelColor = contentColorFor(MaterialTheme.colorScheme.background)

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val width = size.width
            val height = size.height - 45f
            val barWidth = width / 5* 3
            val barHeight = height / 8 * 7
            val barHeight3DPart = height - barHeight
            val barWidth3DPart = (width - barWidth)*(height*0.004f)

            // Front face
            drawPath(
                path = Path().apply {
                    moveTo(0f, height)
                    lineTo(barWidth, height)
                    lineTo(barWidth, height - barHeight)
                    lineTo(0f, height - barHeight)
                    close()
                },
                brush = Brush.linearGradient(
                    colors = listOf(gray, primaryColor)
                )
            )

            // Right face
            drawPath(
                path = Path().apply {
                    moveTo(barWidth, height - barHeight)
                    lineTo(barWidth + barWidth3DPart, 0f)
                    lineTo(barWidth + barWidth3DPart, barHeight)
                    lineTo(barWidth, height)
                },
                brush = Brush.linearGradient(
                    colors = listOf(primaryColor, gray)
                )
            )

            // Top face
            drawPath(
                path = Path().apply {
                    moveTo(0f, barHeight3DPart)
                    lineTo(barWidth, barHeight3DPart)
                    lineTo(barWidth + barWidth3DPart, 0f)
                    lineTo(barWidth3DPart, 0f)
                },
                brush = Brush.linearGradient(
                    colors = listOf(primaryColor, gray)
                )
            )

            // Labels
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    "${(percentage * 100).roundToInt()} %",
                    barWidth/5f,
                    height + 35f,
                    Paint().apply {
                        this.color = labelColor.toArgb()
                        textSize = 11.dp.toPx()
                        isFakeBoldText = true
                    }
                )
            }
        }
    }
}

data class BarChartInput(
    val value: Int,
    val description: String,
    val color: Color,
    val icon: ImageVector
)