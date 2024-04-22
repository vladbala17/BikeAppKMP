package rides.domain.use_case

import rides.domain.RidesDataSource

class DeleteRide(private val rideRepository: RidesDataSource) {
    suspend operator fun invoke(rideName: String) {
        rideRepository.deleteRide(rideName)
    }
}