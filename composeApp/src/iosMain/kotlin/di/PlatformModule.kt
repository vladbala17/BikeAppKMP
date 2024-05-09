package di

import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.vlad.kmp.database.BikesDatabase
import org.koin.dsl.module

actual class PlatformModule {
    actual val modules = module {
        single { NativeSqliteDriver(BikesDatabase.Schema,"bikes.db") }
        single { BikesDatabase(get()) }
    }
}