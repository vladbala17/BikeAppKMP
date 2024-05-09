package di

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.vlad.kmp.database.BikesDatabase
import org.koin.dsl.module

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
    }
}