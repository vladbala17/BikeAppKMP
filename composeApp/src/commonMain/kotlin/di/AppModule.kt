package di

import bikes.domain.BikesDataSource
import settings.domain.KMPPreference

expect class AppModule {
   val localPreferences: KMPPreference
   val bikesDataSource: BikesDataSource
}