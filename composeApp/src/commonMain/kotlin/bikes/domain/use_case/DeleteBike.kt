package bikes.domain.use_case

import bikes.domain.BikesDataSource

class DeleteBike(private val bikeRepository: BikesDataSource) {
    suspend operator fun invoke(bikeName: String) {
        bikeRepository.deleteBike(bikeName)
    }
}