package bikes.domain.use_case

import bikes.domain.BikesDataSource
import bikes.domain.model.Bike

class AddBike(
    private val repository: BikesDataSource
) {
    suspend operator fun invoke(
        bike: Bike
    ) {
        repository.addBike(bike)
    }
}