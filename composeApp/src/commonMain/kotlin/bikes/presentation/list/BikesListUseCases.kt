package bikes.presentation.list

import bikes.domain.use_case.DeleteBike
import bikes.domain.use_case.GetBikes
import bikes.domain.use_case.GetRidesForBike

data class BikesListUseCases(
     val getBikes: GetBikes,
     val deleteBike: DeleteBike,
     val getRidesForBike: GetRidesForBike
)