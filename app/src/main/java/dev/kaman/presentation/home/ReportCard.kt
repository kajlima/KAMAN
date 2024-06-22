package dev.kaman.presentation.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kaman.core.domain.model.report.ReportList
import dev.kaman.presentation.theme.FontPlusJakarta
import dev.kaman.util.formatDateString

@Composable
fun ReportCard(
    report: ReportList
) {
    var isExpanded by remember { mutableStateOf(report.expanded) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFFFFFFF),
            contentColor = Color(0xFF000000)
        ),
        modifier = Modifier
            .animateContentSize(spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessLow))
            .fillMaxWidth()
            .clickable {
                isExpanded = !isExpanded
            }
            .height(if (isExpanded) IntrinsicSize.Max else IntrinsicSize.Min)
            .padding(horizontal = 8.dp, vertical = 8.dp),
    ) {
        Spacer(
            Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(Color(0xFF3477EB))
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 8.dp)
        ) {
            Row {
                Text(
                    text = report.name,
                    style = TextStyle(
                        color = Color(0xFF000000),
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        fontFamily = FontPlusJakarta
                    )
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "·",
                    style = TextStyle(
                        color = Color(0xFF000000),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        fontFamily = FontPlusJakarta
                    )
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = formatDateString(report.incidentDate),
                    style = TextStyle(
                        color = Color(0xFF000000),
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        fontFamily = FontPlusJakarta
                    )
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = report.location,
                style = TextStyle(
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    fontFamily = FontPlusJakarta
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = report.report,
                style = TextStyle(
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    fontFamily = FontPlusJakarta
                ),
                lineHeight = 20.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = if (isExpanded) Int.MAX_VALUE else 2
            )
            if (!isExpanded) {
                Spacer(Modifier.height(16.dp))
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFCAC4D0))
                )
                Spacer(Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Lanjut baca",
                        style = TextStyle(
                            color = Color(0x50000000),
                            fontWeight = FontWeight.Normal,
                            fontSize = 11.sp,
                            fontFamily = FontPlusJakarta
                        )
                    )
                    Spacer(Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFF1C1B1F)
                    )
                }
                Spacer(Modifier.height(2.dp))
            } else {
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
