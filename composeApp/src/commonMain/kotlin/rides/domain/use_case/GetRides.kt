package rides.domain.use_case

import kotlinx.coroutines.flow.Flow
import rides.domain.RidesDataSource
import rides.domain.model.Ride


class GetRides constructor(private val rideRepository: RidesDataSource) {

    operator fun invoke(): Flow<List<Ride>> {
        return rideRepository.getAllRides()
    }
}