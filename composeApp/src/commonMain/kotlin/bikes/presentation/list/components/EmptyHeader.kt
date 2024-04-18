import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.add_bike_label
import bikeappkmp.composeapp.generated.resources.add_ride_label
import bikeappkmp.composeapp.generated.resources.dotted_line
import bikeappkmp.composeapp.generated.resources.missing_bike_card
import bikeappkmp.composeapp.generated.resources.no_bikes_info
import bikes.presentation.list.components.ActionButton
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun EmptyHeader(
    pageTitle: String = "Bikes", icon: DrawableResource = Res.drawable.missing_bike_card,
    showText: Boolean = true,
    onButtonClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "bike",
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f)
        ) {
            Image(
                painter = painterResource(Res.drawable.dotted_line),
                contentDescription = "dotted line",
                modifier = Modifier
                    .padding(start = 20.dp).heightIn(min = 150.dp, max = 150.dp)

            )
            if (showText) {
                Text(
                    text = stringResource(Res.string.no_bikes_info),
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
        ActionButton(
            text = if (showText) {
                stringResource(Res.string.add_bike_label)
            } else {
                stringResource(Res.string.add_ride_label)
            },
            onButtonClick = onButtonClick,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
