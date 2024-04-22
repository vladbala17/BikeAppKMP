package rides.data.mapper

import database.RideEntity
import rides.domain.model.Ride

fun RideEntity.toRide(): Ride {
    return Ride(
        id = rideId,
        bikeName = bikeName,
        rideName = rideName,
        bikeType = bikeType,
        distance = distance,
        durationHours = durationHours,
        durationMinutes = durationMinutes,
        date = date
    )
}