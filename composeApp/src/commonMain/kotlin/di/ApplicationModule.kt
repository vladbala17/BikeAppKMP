package di

import bikes.data.BikesRepository
import bikes.domain.BikesDataSource
import bikes.domain.use_case.AddBike
import bikes.domain.use_case.DeleteBike
import bikes.domain.use_case.GetBikeByName
import bikes.domain.use_case.GetBikeDetail
import bikes.domain.use_case.GetBikes
import bikes.domain.use_case.GetRidesForBike
import bikes.domain.use_case.ValidateBikeName
import bikes.domain.use_case.ValidateDistance
import bikes.presentation.detail.AddBikeUseCases
import bikes.presentation.detail.BikeDetailUseCases
import bikes.presentation.list.BikesListUseCases
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import rides.data.RidesRepository
import rides.domain.RidesDataSource
import rides.domain.use_case.AddRide
import rides.domain.use_case.DeleteRide
import rides.domain.use_case.GetRideDetail
import rides.domain.use_case.GetRides
import rides.presentation.addride.AddRidesUseCases
import rides.presentation.list.GetRidesUseCases
import settings.data.PreferenceRepoImpl
import settings.domain.PreferencesRepo

expect class PlatformModule constructor() {
    val modules: Module
}

fun initKoin() {
    startKoin {
        modules()
    }
}

val bikesUseCasesModule = module {
    single { GetBikes(get()) }
    single { AddBike(get()) }
    single { DeleteBike(get()) }
    single { GetBikeByName(get()) }
    single { GetBikeDetail(get()) }
    single { GetRidesForBike(get()) }
    single { ValidateBikeName() }
    single { ValidateDistance() }

    single { BikesListUseCases(get(), get(), get()) }
    single { AddBikeUseCases(get(), get(), get(), get()) }
    single { BikeDetailUseCases(get(), get()) }
    single { AddBikeUseCases(get(), get(), get(), get()) }
}

val ridesUseCasesModule = module {
    single { AddRide(get()) }
    single { DeleteRide(get()) }
    single { GetRideDetail(get()) }
    single { GetRides(get()) }

    single { AddRidesUseCases(get(), get(), get(), get(), get()) }
    single { GetRidesUseCases(get(), get()) }
}

val repositoriesModule = module {
    single<BikesDataSource> { BikesRepository(get()) }
    single<RidesDataSource> { RidesRepository(get()) }
}

val localPreferencesModule = module {
    single<PreferencesRepo> { PreferenceRepoImpl(get()) }
}
