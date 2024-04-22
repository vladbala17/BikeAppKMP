package bikes.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.km_label
import bikeappkmp.composeapp.generated.resources.no_rides_yet
import bikeappkmp.composeapp.generated.resources.rides_title
import bikeappkmp.composeapp.generated.resources.service_in_label
import bikeappkmp.composeapp.generated.resources.total_rides_distance_label
import bikeappkmp.composeapp.generated.resources.total_rides_label
import bikeappkmp.composeapp.generated.resources.wheels_label
import bikes.presentation.addbike.components.BikeFactory
import bikes.presentation.list.components.LinearProgressBar
import cafe.adriel.voyager.core.screen.Screen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import rides.domain.model.Ride
import rides.presentation.list.components.RideCard

@OptIn(ExperimentalResourceApi::class)
data class BikeDetailScreen(val bikeId: Int = 0) : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel(
            key = "bike-detail-screen",
            factory = viewModelFactory { BikeDetailViewModel() })
        val state by viewModel.state.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BikeFactory(bodyColor = state.bikeColor, bodyType = state.bikeType)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(Res.string.wheels_label),
                )
                Text(
                    text = state.wheelSize,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(Res.string.service_in_label) + " ",
                )
                androidx.compose.material.Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(state.remainingServiceKm.toString())
                        }
                        append(stringResource(Res.string.km_label))
                    },
                )
            }
            LinearProgressBar(
                progress = state.usageUntilService,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(Res.string.total_rides_label) + " ",
                )
                androidx.compose.material.Text(
                    text = if (state.totalRides == 0) {
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(stringResource(Res.string.no_rides_yet))
                            }
                        }
                    } else {
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(state.totalRides.toString())
                            }
                            append(stringResource(Res.string.km_label))
                        }
                    },
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(Res.string.total_rides_distance_label) + " ",
                )
                Text(
                    text = if (state.totalRidesDistance == 0) {
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(stringResource(Res.string.no_rides_yet))
                            }
                        }
                    } else {
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(state.totalRidesDistance.toString())
                            }
                            append(stringResource(Res.string.km_label))
                        }
                    },
                )
            }

            Text(
                text = stringResource(Res.string.rides_title).uppercase(),
                color = Color.Gray,
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 8.dp)
            )

            state.rideList.forEach { ride: Ride ->
                RideCard(
                    rideTitle = ride.rideName,
                    bikeName = ride.bikeName,
                    distance = ride.distance,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                    withContextMenu = false,
                    backgroundColor = androidx.compose.material.MaterialTheme.colors.background,
                    durationHours = ride.durationHours,
                    durationMinutes = ride.durationMinutes,
                    date = ride.date
                )
            }
        }
    }
}
