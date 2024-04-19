package bikes.domain

import bikes.domain.model.Bike
import kotlinx.coroutines.flow.Flow
import rides.domain.model.Ride

interface BikesDataSource {
    suspend fun addBike(bike: Bike)

    suspend fun getBikeById(bikeId: Int): Bike

    suspend fun deleteBike(bikeName: String)

    suspend fun getAllRidesForBike(bikeName: String): List<Ride>

    suspend fun getBikeByName(bikeName: String): Bike

    fun getBikes(): Flow<List<Bike>>
}