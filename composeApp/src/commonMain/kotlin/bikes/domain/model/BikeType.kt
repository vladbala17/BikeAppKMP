package bikes.domain.model

sealed class BikeType(val type: String) {
    object MTB : BikeType("MTB")
    object RoadBike : BikeType("RoadBike")
    object Electric : BikeType("Electric")
    object Hybrid : BikeType("Hybrid")


    companion object {
        fun fromString(type: String): BikeType {
            return when (type) {
                "MTB" -> MTB
                "RoadBike" -> RoadBike
                "Electric" -> Electric
                "Hybrid" -> Hybrid
                else -> MTB
            }
        }
    }
}
