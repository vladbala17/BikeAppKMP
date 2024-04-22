package rides.data.mapper

import database.RideEntity
import rides.domain.model.Ride

fun Ride.toRideEntity(): RideEntity {
    return RideEntity(
        rideId = id,
        rideName = rideName,
        bikeName = bikeName,
        bikeType = bikeType,
        distance = distance,
        durationHours = durationHours,
        durationMinutes = durationMinutes,
        date = date
    )
}