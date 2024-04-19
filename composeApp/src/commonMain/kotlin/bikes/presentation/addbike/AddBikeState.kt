package bikes.presentation.addbike

import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.bike_electric_big_wheels
import bikeappkmp.composeapp.generated.resources.bike_electric_middle
import bikeappkmp.composeapp.generated.resources.bike_electric_over
import bikeappkmp.composeapp.generated.resources.bike_hybrid_big_wheels
import bikeappkmp.composeapp.generated.resources.bike_hybrid_middle
import bikeappkmp.composeapp.generated.resources.bike_hybrid_over
import bikeappkmp.composeapp.generated.resources.bike_mtb_big_wheels
import bikeappkmp.composeapp.generated.resources.bike_mtb_middle
import bikeappkmp.composeapp.generated.resources.bike_mtb_over
import bikeappkmp.composeapp.generated.resources.bike_roadbike_big_wheels
import bikeappkmp.composeapp.generated.resources.bike_roadbike_middle
import bikeappkmp.composeapp.generated.resources.bike_roadbike_over
import bikes.domain.model.BikeType
import bikes.domain.model.PagerBike
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.theme.Green
import ui.theme.Pink
import ui.theme.Red
import ui.theme.Yellow

data class AddBikeState(
    val bikeType: BikeType = BikeType.Electric,
    val bikeName: String = "",
    val bikeTitle: String = BikeType.Electric.type,
    val bikeNameError: String? = null,
    val distanceError: String? = null,
    val bikeColor: Int = White.toArgb(),
    val wheelSize: String = "29'",
    val serviceIn: String = "",
    val defaultUnit: String = "",
    val isDefault: Boolean = false,
    val bikePagerList: List<PagerBike> = buildPagerBikeList(),
    val selectedBike: Int = 0,
    val isValidatedSuccessfully: Boolean = false
)


@OptIn(ExperimentalResourceApi::class)
fun buildPagerBikeList() = listOf(
    PagerBike(
        title = BikeType.RoadBike.type,
        type = BikeType.RoadBike,
        wheels = Res.drawable.bike_roadbike_big_wheels,
        middle = Res.drawable.bike_roadbike_middle,
        over = Res.drawable.bike_roadbike_over,
        color = Yellow.toArgb()
    ),
    PagerBike(
        title = BikeType.MTB.type,
        type = BikeType.MTB,
        wheels = Res.drawable.bike_mtb_big_wheels,
        middle = Res.drawable.bike_mtb_middle,
        over = Res.drawable.bike_mtb_over,
        color = Green.toArgb()
    ),
    PagerBike(
        title = BikeType.Electric.type,
        type = BikeType.Electric,
        wheels = Res.drawable.bike_electric_big_wheels,
        middle = Res.drawable.bike_electric_middle,
        over = Res.drawable.bike_electric_over,
        color = Red.toArgb()
    ),
    PagerBike(
        title = BikeType.Hybrid.type,
        type = BikeType.Hybrid,
        wheels = Res.drawable.bike_hybrid_big_wheels,
        middle = Res.drawable.bike_hybrid_middle,
        over = Res.drawable.bike_hybrid_over,
        color = Pink.toArgb()
    )
)

