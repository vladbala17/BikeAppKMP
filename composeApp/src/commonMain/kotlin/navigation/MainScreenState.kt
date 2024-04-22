package navigation

import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.add_bike_label
import bikeappkmp.composeapp.generated.resources.bikes_title
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@ExperimentalResourceApi
data class MainScreenState(
    val title: StringResource = Res.string.bikes_title,
    val showNavigationIcon: Boolean = false,
    val actionText: StringResource = Res.string.add_bike_label,
    val showActionIconAdd: Boolean = true,
    val showActionIconX: Boolean = false,
    val showActionText: Boolean = true,
    val showBottomNavigationBar: Boolean = true,
    val showMenuIcon: Boolean = false
)
