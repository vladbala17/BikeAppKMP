package di

import bikes.domain.BikesDataSource
import rides.domain.RidesDataSource
import settings.domain.KMPPreference

expect class AppModule {
   val localPreferences: KMPPreference
   val bikesDataSource: BikesDataSource
   val ridesDataSource: RidesDataSource
}