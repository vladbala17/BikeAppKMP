package rides.presentation.addride

import bikes.domain.model.Bike
import bikes.domain.model.BikeType
import kotlinx.datetime.Clock

data class AddRideState(
    val rideName: String = "",
    val bikeName: String = "",
    val bikeType: BikeType = BikeType.RoadBike,
    val distance: String = "",
    val durationHours: Int = 2,
    val durationMinutes: Int = 24,
    val defaultUnit: String = "",
    val date: Long = Clock.System.now().toEpochMilliseconds(),
    val rideNameError: String? = null,
    val distanceError: String? = null,
    val bikeNamesList: List<Bike> = emptyList(),
    val showDurationPicker: Boolean = false,
    val showDatePicker: Boolean = false,
    val isValidatedSuccessfully: Boolean = false
)
