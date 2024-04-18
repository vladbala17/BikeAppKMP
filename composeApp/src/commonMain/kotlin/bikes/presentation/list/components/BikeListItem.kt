package bikes.presentation.list.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import bikes.domain.model.Bike


@Composable
fun BikeListItem(
    bike: Bike,
    remainingServiceDistance: Int = 0,
    usageUntilService: Float = 0.0f,
    onEditBikeMenuClick: (Int) -> Unit,
    onDeleteBike: (String) -> Unit,
    onBikeItemClick: (Int) -> Unit,
    defaultUnit: String = "",
    modifier: Modifier
) {
    BikeCard(
        bikeId = bike.id,
        bikeName = bike.name,
        wheelSize = bike.wheelSize,
        bikeType = bike.bikeType,
        bikeColor = Color(bike.bikeColor),
        remainingServiceDistance = remainingServiceDistance,
        usageUntilService = usageUntilService,
        defaultUnit = defaultUnit,
        modifier = modifier,
        onEditBikeMenuClick = onEditBikeMenuClick,
        onDeleteBikeMenuClick = onDeleteBike,
        onBikeCardClick = onBikeItemClick
    )
}

//bike.serviceIn.toFloat() / (bike.serviceIn.toFloat() - bike.remainingServiceDistance.toFloat())
