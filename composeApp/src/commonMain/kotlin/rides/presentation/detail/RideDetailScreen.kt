package rides.presentation.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.icon_bikes_inactive
import bikeappkmp.composeapp.generated.resources.ride_bike_label
import bikeappkmp.composeapp.generated.resources.ride_date_label
import bikeappkmp.composeapp.generated.resources.ride_distance_label
import bikeappkmp.composeapp.generated.resources.ride_duration_label
import cafe.adriel.voyager.core.screen.Screen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
data class RideDetailScreen(val rideId: Int = 0) : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel(
            key = "ride-detail-screen",
            factory = viewModelFactory { RideDetailViewModel() })
        val state by viewModel.state.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

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
                    text = state.rideTitle,
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = stringResource(Res.string.ride_bike_label) + ":",
                )
                Text(
                    text = state.bikeName,
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = stringResource(Res.string.ride_distance_label) + ":",
                )
                Text(
                    text = state.distance + "km",
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = stringResource(Res.string.ride_duration_label) + ":",
                )
                Text(
                    text = state.duration,
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = stringResource(Res.string.ride_date_label) + ":",
                )
                Text(
                    text = state.date,
                )
            }
        }
    }
}