package rides.presentation.list

import rides.domain.use_case.DeleteRide
import rides.domain.use_case.GetRides

data class GetRidesUseCases(val getRides: GetRides,
                            val deleteRide: DeleteRide)
