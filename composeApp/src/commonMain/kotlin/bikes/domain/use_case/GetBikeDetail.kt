package bikes.domain.use_case

import bikes.domain.BikesDataSource
import bikes.domain.model.Bike


class GetBikeDetail(private val bikeRepository: BikesDataSource) {
    suspend operator fun invoke(bikeId: Int): Bike {
        return bikeRepository.getBikeById(bikeId)
    }
}