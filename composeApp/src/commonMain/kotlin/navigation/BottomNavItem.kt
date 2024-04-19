@file:OptIn(ExperimentalResourceApi::class)
@file:Suppress("OPT_IN_USAGE_FUTURE_ERROR")

package navigation

import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource


data class BottomNavItem(
    val route: Screen,
    val selectedIcon: DrawableResource,
    val unselectedIcon: DrawableResource,
    val titleId: StringResource
)


