package rides.presentation.list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.icon_bikes_inactive
import bikeappkmp.composeapp.generated.resources.icon_more
import bikeappkmp.composeapp.generated.resources.ride_bike_label
import bikeappkmp.composeapp.generated.resources.ride_date_label
import bikeappkmp.composeapp.generated.resources.ride_distance_label
import bikeappkmp.composeapp.generated.resources.ride_duration
import bikeappkmp.composeapp.generated.resources.ride_duration_label
import bikes.presentation.list.components.CardDropDownMenu
import kotlinx.datetime.Clock
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import util.convertMillisToDateMonthNumber


@OptIn(ExperimentalResourceApi::class)
@Composable
fun RideCard(
    rideId: Int = 0,
    rideTitle: String = "Faget MTB Tour",
    distance: Int = 0,
    durationHours: Int = 2,
    durationMinutes: Int = 8,
    date: Long = Clock.System.now().toEpochMilliseconds(),
    bikeName: String = "Nukeproof Scout 290",
    onEditRideMenuClick: (Int) -> Unit = {},
    onDeleteRideMenuClick: (String) -> Unit = {},
    onRideCardClick: (Int) -> Unit = {},
    withContextMenu: Boolean = true,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        var displayMenu by remember { mutableStateOf(false) }
        Column(modifier = Modifier
            .padding(16.dp)
            .clickable { onRideCardClick(rideId) }) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(
                    painter = painterResource(Res.drawable.icon_bikes_inactive),
                    contentDescription = "ride card menu icon",
                    modifier = Modifier
                        .border(BorderStroke(1.dp, Color.White), CircleShape)
                        .padding(4.dp)
                        .clip(
                            CircleShape
                        )
                )
                Text(
                    text = rideTitle,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                if (withContextMenu) {
                    Box {
                        IconButton(
                            onClick = {
                                displayMenu = !displayMenu
                            }) {
                            Icon(
                                painter = painterResource(Res.drawable.icon_more),
                                contentDescription = "bike card menu",
                            )
                        }
                        CardDropDownMenu(
                            displayMenu = displayMenu,
                            onDismissRequest = { displayMenu = false },
                            itemId = rideId,
                            onEditItemClick = onEditRideMenuClick,
                            onDeleteItemClick = onDeleteRideMenuClick
                        )
                    }
                }
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = stringResource(Res.string.ride_bike_label) + ": ",
                )
                Text(
                    text = bikeName,
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = stringResource(Res.string.ride_distance_label) + ":",
                )
                Text(
                    text = distance.toString() + "km",
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = stringResource(Res.string.ride_duration_label) + ": ",
                )
                Text(
                    text = stringResource(
                        Res.string.ride_duration,
                        durationHours,
                        durationMinutes
                    ),
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = stringResource(Res.string.ride_date_label) + ": ",
                )
                Text(
                    text = convertMillisToDateMonthNumber(date),
                )
            }
        }
    }
}