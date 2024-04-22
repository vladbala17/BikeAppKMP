package rides.domain

import kotlinx.coroutines.flow.Flow
import rides.domain.model.Ride

interface RidesDataSource {
    fun getAllRides(): Flow<List<Ride>>

    suspend fun getRideById(rideId: Int): Ride

    suspend fun deleteRide(rideName: String)

    suspend fun addRide(ride: Ride)
}