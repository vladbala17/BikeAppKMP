package bikes.presentation.detail

import androidx.compose.ui.graphics.Color
import bikes.domain.model.BikeType
import rides.domain.model.Ride

data class BikeDetailState(
    val bikeId: String = "",
    val bikeType: BikeType = BikeType.Electric,
    val bikeColor: Color = Color.Red,
    val wheelSize: String = "",
    val remainingServiceKm: Int = 0,
    val totalRides: Int = 0,
    val totalRidesDistance: Int = 0,
    val usageUntilService: Float = 0.8f,
    val rideList: List<Ride> = emptyList()
)
