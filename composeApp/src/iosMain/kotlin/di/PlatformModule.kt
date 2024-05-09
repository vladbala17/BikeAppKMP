package di

import bikes.presentation.addbike.AddBikeViewModel
import bikes.presentation.detail.BikeDetailViewModel
import bikes.presentation.list.BikesViewModel
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.vlad.kmp.database.BikesDatabase
import org.koin.dsl.module
import rides.presentation.addride.AddRideViewModel
import rides.presentation.detail.RideDetailViewModel
import rides.presentation.list.RidesViewModel

actual class PlatformModule {
    actual val modules = module {
        single { NativeSqliteDriver(BikesDatabase.Schema, "bikes.db") }
        single { BikesDatabase(get()) }

        factory { AddBikeViewModel(get(), get(), get()) }
        factory { BikeDetailViewModel(get(), get()) }
        factory { BikesViewModel(get(), get()) }

        factory { AddRideViewModel(get(), get(), get()) }
        factory { RideDetailViewModel() }
        factory { RidesViewModel(get()) }
    }
}