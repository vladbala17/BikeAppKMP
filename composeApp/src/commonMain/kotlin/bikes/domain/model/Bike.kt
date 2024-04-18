package bikes.domain.model

data class Bike(
    val id: Int,
    val name: String,
    val wheelSize: String,
    val serviceIn: Int,
    val isDefault: Boolean,
    val bikeType: BikeType,
    val bikeColor: Int,
    val remainingServiceDistance: Int = 0,
    val usageUntilService: Float = 0.0f,
)
