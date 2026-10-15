package com.raghav.gitbucks.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raghav.gitbucks.domain.models.LanguageStat

private val LANGUAGE_COLORS = listOf(
    Color(0xFF6366F1), Color(0xFFEC4899), Color(0xFF14B8A6),
    Color(0xFFF59E0B), Color(0xFF10B981), Color(0xFF3B82F6),
    Color(0xFFF97316), Color(0xFF8B5CF6)
)

@Composable
fun LanguagePieChart(stats: List<LanguageStat>) {
    if (stats.isEmpty()) return

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
            .padding(20.dp)
    ) {
        Text(
            text = "Language Distribution",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            // Pie chart
            Canvas(modifier = Modifier.size(140.dp)) {
                var startAngle = -90f
                stats.forEachIndexed { i, stat ->
                    val sweep = (stat.percentage / 100.0 * 360.0).toFloat()
                    drawArc(
                        color = LANGUAGE_COLORS[i % LANGUAGE_COLORS.size],
                        startAngle = startAngle,
                        sweepAngle = sweep,
                        useCenter = true
                    )
                    startAngle += sweep
                }
            }

            Spacer(Modifier.width(20.dp))

            // Legend
            Column(verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(6.dp)) {
                stats.forEachIndexed { i, stat ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .background(LANGUAGE_COLORS[i % LANGUAGE_COLORS.size], CircleShape)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "${stat.language} ${(stat.percentage * 10).toLong() / 10.0}%",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
                        )
                    }
                }
            }
        }
    }
}
