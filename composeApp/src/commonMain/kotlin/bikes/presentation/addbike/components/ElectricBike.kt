

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.bike_electric_big_wheels
import bikeappkmp.composeapp.generated.resources.bike_electric_middle
import bikeappkmp.composeapp.generated.resources.bike_electric_over
import bikeappkmp.composeapp.generated.resources.electric_bike_name
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ElectricBike(bodyColor: Color = Color.Red, modifier: Modifier) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(Res.drawable.bike_electric_big_wheels),
            contentDescription = stringResource(Res.string.electric_bike_name),
            modifier = Modifier.aspectRatio(16f / 9f),
        )
        Image(
            painter = painterResource(Res.drawable.bike_electric_middle),
            contentDescription = stringResource(Res.string.electric_bike_name),
            colorFilter = ColorFilter.tint(bodyColor),
            modifier = Modifier.aspectRatio(16f / 9f)
        )
        Image(
            painter = painterResource(Res.drawable.bike_electric_over),
            contentDescription = stringResource(Res.string.electric_bike_name),
            modifier = Modifier.aspectRatio(16f / 9f)
        )
    }
}