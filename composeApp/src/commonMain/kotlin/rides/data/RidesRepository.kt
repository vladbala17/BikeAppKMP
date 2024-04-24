package rides.data

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.vlad.kmp.database.BikesDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import rides.data.mapper.toRide
import rides.domain.RidesDataSource
import rides.domain.model.Ride

class RidesRepository(db: BikesDatabase) : RidesDataSource {
    private val queries = db.rideQueries
    override fun getAllRides(): Flow<List<Ride>> {
        return queries.getRides().asFlow().mapToList()
            .map { entities -> entities.map { it.toRide() } }
    }

    override suspend fun getRideById(rideId: Int): Ride {
        return queries.getRideById(rideId).executeAsOne().toRide()
    }

    override suspend fun deleteRide(rideName: String) {
        return queries.deleteRide(rideName)
    }

    override suspend fun addRide(ride: Ride) {
        queries.insertRideEntity(
            rideId = if (ride.id == 0) null else ride.id,
            rideName = ride.rideName,
            bikeName = ride.bikeName,
            bikeType = ride.bikeType,
            distance = ride.distance,
            durationHours = ride.durationHours,
            durationMinutes = ride.durationMinutes,
            date = ride.date
        )
    }
}