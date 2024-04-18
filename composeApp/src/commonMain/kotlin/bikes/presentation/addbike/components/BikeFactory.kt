package bikes.presentation.addbike.components

import ElectricBike
import HybridBike
import RoadBike
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import bikes.domain.model.BikeType
import com.vlad.bikegarage.bikes.presentation.addbikes.components.MTBBike

@Composable
fun BikeFactory(
    bodyColor: Color = Color.Red,
    bodyType: BikeType = BikeType.Electric,
    modifier: Modifier = Modifier,
    onPageChanged: () -> Unit = {}
) {
    createBikeType(bodyType, bodyColor = bodyColor, modifier = modifier)
}

@Composable
fun createBikeType(type: BikeType = BikeType.Electric, modifier: Modifier, bodyColor: Color) {
    when (type) {
        BikeType.Electric -> ElectricBike(modifier = modifier, bodyColor = bodyColor)
        BikeType.Hybrid -> HybridBike(modifier = modifier, bodyColor = bodyColor)
        BikeType.MTB -> MTBBike(modifier = modifier, bodyColor = bodyColor)
        BikeType.RoadBike -> RoadBike(modifier = modifier, bodyColor = bodyColor)
    }
}