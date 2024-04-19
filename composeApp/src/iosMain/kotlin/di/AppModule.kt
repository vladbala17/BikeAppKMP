package di

import platform.darwin.NSObject
import settings.domain.KMPPreference

actual class AppModule(
    private val appContext: NSObject
) {
    actual val localPreferences: KMPPreference = KMPPreference(appContext)
}