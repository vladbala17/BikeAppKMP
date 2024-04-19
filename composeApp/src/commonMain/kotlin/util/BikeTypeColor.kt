package util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import bikes.domain.model.BikeType
import ui.theme.Orange
import ui.theme.Red
import ui.theme.Yellow

fun getColorByType(bikeType: BikeType): Color {
    return when (bikeType) {
        BikeType.Electric -> White
        BikeType.Hybrid -> Yellow
        BikeType.MTB -> Orange
        BikeType.RoadBike -> Red
    }
}