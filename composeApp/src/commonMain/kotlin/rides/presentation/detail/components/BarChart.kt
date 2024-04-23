@file:OptIn(ExperimentalResourceApi::class)

package rides.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.chart_title
import bikeappkmp.composeapp.generated.resources.icon_stats
import bikeappkmp.composeapp.generated.resources.total_chart_label
import bikeappkmp.composeapp.generated.resources.total_km_chart_label
import bikes.domain.model.BikeType
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import rides.domain.model.RideChartRow
import ui.theme.DarkBlue
import ui.theme.Gray
import util.getColorByType

@Composable
fun BarChart(
    inputData: List<RideChartRow> = previewList,
    totalKm: Int = 25570,
    maxHeight: Dp = 200.dp,
    titleColor: Color = Color.White,
    borderColor: Color = Gray,
    barTextColor: Color = DarkBlue,
    modifier: Modifier = Modifier
) {

    val strokeWidth = 1.dp


    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(Res.drawable.icon_stats),
                contentDescription = "ride card menu icon",
            )
            Text(
                text = stringResource(Res.string.chart_title),
                color = titleColor,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Row(
            modifier = modifier.then(
                Modifier
                    .fillMaxWidth()
                    .height(maxHeight)
                    .drawBehind {
                        // X- Axis

                        drawLine(
                            color = borderColor,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 2.dp.toPx()
                        )

                        // Y- Axis
                        drawLine(
                            color = borderColor,
                            start = Offset(0f, 0f),
                            end = Offset(0f, size.height),
                            strokeWidth = strokeWidth.toPx()
                        )
                        repeat(11) {

                            drawLine(
                                color = borderColor,
                                start = Offset(0f, size.height - it * size.height / 10),
                                end = Offset(size.width, size.height - it * size.height / 10),
                                strokeWidth = strokeWidth.toPx()
                            )
                        }
                    },
            ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            inputData.forEach { item ->
                Bar(
                    itemName = item.km.toString(),
                    value = item.km,
                    barColor = getColorByType(item.type),
                    barTextColor = barTextColor,
                    totalKm = totalKm,
                    totalHeight = maxHeight
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, borderColor),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            inputData.forEach { item ->
                Text(
                    text = item.type.type,
                    color = borderColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.25f)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(Res.string.total_chart_label),
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = stringResource(Res.string.total_km_chart_label, totalKm),
            )
        }

    }

}

@Composable
private fun RowScope.Bar(
    itemName: String,
    value: Int,
    barColor: Color,
    barTextColor: Color,
    totalKm: Int,
    totalHeight: Dp
) {

    val itemHeight = remember(value) { value * 100 / totalKm }

    val barHeight = (totalHeight * itemHeight / 100).value
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .padding(horizontal = 5.dp)
            .fillMaxWidth(0.25f)
            .height(barHeight.dp)
            .background(barColor)
    ) {
        Text(text = itemName, Modifier.align(Alignment.BottomCenter), color = barTextColor)
    }
}


val previewList = listOf(
    RideChartRow(BikeType.RoadBike, 8500),
    RideChartRow(BikeType.MTB, 2650),
    RideChartRow(BikeType.Hybrid, 3420),
    RideChartRow(BikeType.Electric, 11000),
)



