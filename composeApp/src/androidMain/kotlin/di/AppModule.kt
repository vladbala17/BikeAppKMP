package di

import android.app.Application
import bikes.data.BikesRepository
import bikes.domain.BikesDataSource
import com.vlad.kmp.database.BikesDatabase
import core.data.DatabaseDriverFactory
import rides.data.RidesRepository
import rides.domain.RidesDataSource
import settings.domain.KMPPreference

actual class AppModule(
    private val appContext: Application
) {
    actual val localPreferences: KMPPreference = KMPPreference(appContext)
    val sqlDriver = DatabaseDriverFactory(appContext).create()
    val database = BikesDatabase(sqlDriver)
    actual val bikesDataSource: BikesDataSource by lazy {
        BikesRepository(db = database)
    }

    actual val ridesDataSource: RidesDataSource by lazy {
        RidesRepository(db = database)
    }
}