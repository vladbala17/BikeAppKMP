package rides.presentation.addride

import bikes.domain.use_case.GetBikes
import bikes.domain.use_case.ValidateBikeName
import bikes.domain.use_case.ValidateDistance
import rides.domain.use_case.AddRide
import rides.domain.use_case.GetRideDetail

data class AddRidesUseCases(
    val rideNameUseCase: ValidateBikeName,
    val validateDistance: ValidateDistance,
    val addRide: AddRide,
    val getRideDetail: GetRideDetail,
    val getBikes: GetBikes
)
