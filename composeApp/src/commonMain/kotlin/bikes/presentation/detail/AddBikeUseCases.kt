package bikes.presentation.detail

import bikes.domain.use_case.AddBike
import bikes.domain.use_case.GetBikeDetail
import bikes.domain.use_case.ValidateBikeName
import bikes.domain.use_case.ValidateDistance

data class AddBikeUseCases(
    val addBike: AddBike,
    val getBikeDetail: GetBikeDetail,
    val bikeNameUseCase: ValidateBikeName,
    val validDistance: ValidateDistance
)