package di

import android.app.Application
import bikes.data.BikesRepository
import bikes.domain.BikesDataSource
import com.vlad.kmp.database.BikesDatabase
import core.data.DatabaseDriverFactory
import settings.domain.KMPPreference

actual class AppModule(
    private val appContext: Application
) {
    actual val localPreferences: KMPPreference = KMPPreference(appContext)

    actual val bikesDataSource: BikesDataSource by lazy {
        BikesRepository(db = BikesDatabase(driver = DatabaseDriverFactory(appContext).create()))
    }
}