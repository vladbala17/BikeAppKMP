package bikes.domain.use_case

import bikes.domain.BikesDataSource
import bikes.domain.model.Bike

class GetBikeByName(private val bikeRepository: BikesDataSource) {

    suspend operator fun invoke(bikeName: String): Bike {
        return bikeRepository.getBikeByName(bikeName)
    }
}