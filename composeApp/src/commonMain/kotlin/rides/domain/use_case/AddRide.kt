package rides.domain.use_case

import rides.domain.RidesDataSource
import rides.domain.model.Ride

class AddRide(private val rideRepository: RidesDataSource) {

    suspend operator fun invoke(ride: Ride) {
        rideRepository.addRide(ride)
    }
}