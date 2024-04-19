package di

import android.app.Application
import settings.domain.KMPPreference

actual class AppModule(
    private val appContext: Application
) {
    actual val localPreferences: KMPPreference = KMPPreference(appContext)

}