package rides.presentation.list

import rides.domain.model.Ride
import rides.domain.model.RideChartRow

data class RidesState(
    val rides: Map<String, List<Ride>> = emptyMap(),
    val showDialog: Boolean = false,
    val rideStatistic: List<RideChartRow> = emptyList(),
    val totalKm: Int = 0,
    val rideName: String = "",
)
