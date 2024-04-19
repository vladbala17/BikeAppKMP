package rides.presentation.list.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import rides.domain.model.Ride

@Composable
fun RideListItem(
    ride: Ride,
    onEditRideMenuClick: (Int) -> Unit,
    onDeleteRide: (String) -> Unit,
    onRideItemClick: (Int) -> Unit,
    modifier: Modifier
) {
    RideCard(
        rideId = ride.id,
        rideTitle = ride.rideName,
        bikeName = ride.bikeName,
        date = ride.date,
        distance = ride.distance,
        durationHours = ride.durationHours,
        durationMinutes = ride.durationMinutes,
        onEditRideMenuClick = onEditRideMenuClick,
        onDeleteRideMenuClick = onDeleteRide,
        onRideCardClick = onRideItemClick,
        modifier = modifier
    )
}