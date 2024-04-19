package bikes.data

import bikes.domain.BikesDataSource
import bikes.domain.model.Bike
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.vlad.bikegarage.bikes.data.local.mapper.toBike
import com.vlad.kmp.database.BikesDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import rides.domain.model.Ride

class BikesRepository(db: BikesDatabase) : BikesDataSource {
    private val queries = db.bikeQueries
    override suspend fun addBike(bike: Bike) {
        queries.insertBikeEntity(
            bikeId = bike.id,
            name = bike.name,
            serviceIn = bike.serviceIn,
            isDefault = if (bike.isDefault) {
                1
            } else {
                0
            },
            bikeType = bike.bikeType.type,
            wheelSize = bike.wheelSize,
            bikeColor = bike.bikeColor
        )
    }

    override suspend fun getBikeById(bikeId: Int): Bike {
        return queries.getBikeById(bikeId).executeAsOne().toBike()
    }

    override suspend fun deleteBike(bikeName: String) {
        queries.deleteBike(name = bikeName)
    }

    override suspend fun getAllRidesForBike(bikeName: String): List<Ride> {
        TODO("Not yet implemented")
    }

    override suspend fun getBikeByName(bikeName: String): Bike {
        return queries.getBikeByName(bikeName).executeAsOne().toBike()
    }

    override fun getBikes(): Flow<List<Bike>> {
        return queries.getBikes().asFlow().mapToList().map { it.map { it.toBike() } }
    }
}