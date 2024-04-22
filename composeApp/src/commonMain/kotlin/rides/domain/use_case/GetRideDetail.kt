package rides.domain.use_case

import rides.domain.RidesDataSource
import rides.domain.model.Ride


class GetRideDetail(private val rideRepository: RidesDataSource) {
    suspend operator fun invoke(rideId: Int): Ride {
        return rideRepository.getRideById(rideId)
    }
}