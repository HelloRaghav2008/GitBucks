package com.raghav.gitbucks.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raghav.gitbucks.domain.models.CommitActivity

@Composable
fun CommitTrendChart(trend: List<CommitActivity>) {
    if (trend.isEmpty()) return

    val maxCount = trend.maxOf { it.count }.coerceAtLeast(minimumValue = 1)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(all = 20.dp)
    ) {
        Text(
            text = "Commit Trend (last 12 months)",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Aggregated across top 5 repos",
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
        Spacer(Modifier.height(16.dp))

        Canvas(modifier = Modifier.fillMaxWidth().height(120.dp)) {
            val totalBars = trend.size
            val spacing = size.width / totalBars
            val barWidth = spacing * 0.65f

            trend.forEachIndexed { i, activity ->
                val barHeight = (activity.count.toFloat() / maxCount) * size.height
                val x = i * spacing + (spacing - barWidth) / 2f
                val y = size.height - barHeight

                drawRect(
                    color = Color(0xFF6366F1),
                    topLeft = Offset(x, y),
                    size = Size(barWidth, barHeight)
                )
            }
        }

        Spacer(Modifier.height(8.dp))
        Text(
            text = if (trend.isNotEmpty()) "${trend.first().month} → ${trend.last().month}" else "",
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f)
        )
    }
}
