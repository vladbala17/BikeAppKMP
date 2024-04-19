package di

import settings.domain.KMPPreference

expect class AppModule {
   val localPreferences: KMPPreference
}