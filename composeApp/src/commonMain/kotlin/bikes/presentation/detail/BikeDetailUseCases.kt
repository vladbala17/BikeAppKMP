package bikes.presentation.detail

import bikes.domain.use_case.GetBikeDetail
import bikes.domain.use_case.GetRidesForBike

data class BikeDetailUseCases(val getBikeDetail: GetBikeDetail, val getRidesForBike: GetRidesForBike)