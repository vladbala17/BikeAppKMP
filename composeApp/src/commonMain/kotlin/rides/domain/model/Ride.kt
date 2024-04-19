package rides.domain.model

data class Ride(
    val id: Int,
    val rideName: String,
    val bikeName: String,
    val bikeType: String,
    val distance: Int,
    val durationHours: Int,
    val durationMinutes: Int,
    val date: Long
)
