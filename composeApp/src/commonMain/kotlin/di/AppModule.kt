package di

import bikes.domain.BikesDataSource
import rides.domain.RidesDataSource
import settings.domain.PreferencesRepo

expect class AppModule {
    val localPreferences: PreferencesRepo
    val bikesDataSource: BikesDataSource
    val ridesDataSource: RidesDataSource
}

