@file:OptIn(ExperimentalResourceApi::class)
@file:Suppress("OPT_IN_USAGE_FUTURE_ERROR")

package navigation

import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.bikes_title
import bikeappkmp.composeapp.generated.resources.icon_bikes_inactive
import bikeappkmp.composeapp.generated.resources.rides_active
import bikeappkmp.composeapp.generated.resources.rides_inactive
import bikeappkmp.composeapp.generated.resources.rides_title
import bikeappkmp.composeapp.generated.resources.settings_active
import bikeappkmp.composeapp.generated.resources.settings_inactive
import bikeappkmp.composeapp.generated.resources.settings_title
import bikes.presentation.list.BikesScreen
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import rides.presentation.list.RidesScreen
import settings.presentation.SettingsScreen


data class BottomNavItem(
    val route: Screen,
    val selectedIcon: DrawableResource,
    val unselectedIcon: DrawableResource,
    val titleId: StringResource
)

val BOTTOM_NAV_ITEM_LIST = listOf(
    BottomNavItem(
        route = BikesScreen(),
        selectedIcon = Res.drawable.icon_bikes_inactive,
        unselectedIcon = Res.drawable.icon_bikes_inactive,
        titleId = Res.string.bikes_title
    ),
    BottomNavItem(
        route = RidesScreen(),
        selectedIcon = Res.drawable.rides_active,
        unselectedIcon = Res.drawable.rides_inactive,
        titleId = Res.string.rides_title
    ),
    BottomNavItem(
        route = SettingsScreen(),
        selectedIcon = Res.drawable.settings_active,
        unselectedIcon = Res.drawable.settings_inactive,
        titleId = Res.string.settings_title
    )
)
