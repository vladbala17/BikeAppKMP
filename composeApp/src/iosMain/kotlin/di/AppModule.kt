package di

import bikes.data.BikesRepository
import bikes.domain.BikesDataSource
import com.vlad.kmp.database.BikesDatabase
import core.data.DatabaseDriverFactory
import platform.darwin.NSObject
import settings.domain.KMPPreference

actual class AppModule(
    private val appContext: NSObject
) {
    actual val localPreferences: KMPPreference = KMPPreference(appContext)

    actual val bikesDataSource: BikesDataSource by lazy {
        BikesRepository(db = BikesDatabase(driver = DatabaseDriverFactory().create()))
    }
}