package bikes.domain.use_case

import bikes.domain.BikesDataSource
import rides.domain.model.Ride

class GetRidesForBike(private val bikeRepository: BikesDataSource) {
    suspend operator fun invoke(bikeName: String): List<Ride> {
        return bikeRepository.getAllRidesForBike(bikeName)
    }
}