package bikes.domain.model

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi


@OptIn(ExperimentalResourceApi::class)
data class PagerBike(
    val title: String,
    val type: BikeType,
    val wheels: DrawableResource,
    val middle: DrawableResource,
    val over: DrawableResource,
    val color: Int = 0
)
