package bikes.domain.use_case

import bikes.domain.BikesDataSource
import bikes.domain.model.Bike
import kotlinx.coroutines.flow.Flow


class GetBikes(private val repository: BikesDataSource) {
    operator fun invoke(): Flow<List<Bike>> {
        return repository.getBikes()
    }
}