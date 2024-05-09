package di

import bikes.presentation.addbike.AddBikeViewModel
import bikes.presentation.detail.BikeDetailViewModel
import bikes.presentation.list.BikesViewModel
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.vlad.kmp.database.BikesDatabase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rides.presentation.addride.AddRideViewModel
import rides.presentation.detail.RideDetailViewModel
import rides.presentation.list.RidesViewModel
import settings.domain.KMPPreference

actual class PlatformModule {
    actual val modules = module {
        single {
            AndroidSqliteDriver(
                BikesDatabase.Schema,
                get(),
                "bikes.db"
            )
        }
        single { BikesDatabase(get()) }

        viewModel { AddBikeViewModel(get(), get(), get()) }
        viewModel { BikeDetailViewModel(get(), get()) }
        viewModel { BikesViewModel(get(), get()) }

        viewModel { AddRideViewModel(get(), get(), get()) }
        viewModel { RideDetailViewModel() }
        viewModel { RidesViewModel(get()) }

        single { KMPPreference(get()) }
    }
}