package di

import bikes.presentation.addbike.AddBikeViewModel
import bikes.presentation.detail.BikeDetailViewModel
import bikes.presentation.list.BikesViewModel
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.vlad.kmp.database.BikesDatabase
import org.koin.dsl.module
import platform.darwin.NSObject
import rides.presentation.addride.AddRideViewModel
import rides.presentation.detail.RideDetailViewModel
import rides.presentation.list.RidesViewModel
import settings.domain.KMPPreference

actual class PlatformModule {
    actual val modules = module {
        single {
            val driver = NativeSqliteDriver(BikesDatabase.Schema, "bikes.db")
            BikesDatabase(driver)
        }

        factory { AddBikeViewModel(get(), get(), get()) }
        factory { BikeDetailViewModel(get(), get()) }
        factory { BikesViewModel(get(), get()) }

        factory { AddRideViewModel(get(), get(), get()) }
        factory { RideDetailViewModel() }
        factory { RidesViewModel(get()) }

        single { KMPPreference(NSObject()) }
    }
}