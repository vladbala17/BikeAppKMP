package rides.presentation.list

import EmptyHeader
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.missing_ride_card
import bikes.presentation.list.components.ConfirmationDialog
import cafe.adriel.voyager.core.screen.Screen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.resources.ExperimentalResourceApi
import rides.domain.model.Ride
import rides.presentation.detail.components.BarChart
import rides.presentation.list.components.RideListItem

@OptIn(ExperimentalResourceApi::class)
data class RidesScreen(
    val onEditRide: (Int) -> Unit = {},
    val onRideClick: (Int) -> Unit = {},
    val onNavigateToScreen: () -> Unit = {}
) : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel(
            key = "rides-screen",
            factory = viewModelFactory { RidesViewModel() })
        val state by viewModel.state.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 8.dp)
                .verticalScroll(rememberScrollState())
                .height(IntrinsicSize.Max)
        ) {
            if (state.rides.isEmpty()) {
                EmptyHeader(
                    icon = Res.drawable.missing_ride_card,
                    showText = false,
                    onButtonClick = { onNavigateToScreen() },
                )
            } else {
                BarChart(inputData = state.rideStatistic, totalKm = state.totalKm)

                state.rides.forEach { (month, ridesForMonth) ->
                    Text(text = month)
                    ridesForMonth.forEach { ride: Ride ->
                        RideListItem(
                            ride = ride,
                            modifier = Modifier.padding(8.dp),
                            onEditRideMenuClick = { onEditRide(ride.id) },
                            onDeleteRide = {
                                viewModel.onEvent(RidesEvent.OnDeleteRide(ride.rideName))
                            },
                            onRideItemClick = onRideClick
                        )
                    }
                }
            }
            if (state.showDialog) {
                ConfirmationDialog(
                    onDismissRequest = { viewModel.onEvent(RidesEvent.OnDismissDialog) },
                    onConfirmation = {
                        viewModel.onEvent(RidesEvent.OnDeleteConfirmation)
                    },
                    dialogText = state.rideName,
                )
            }
        }
    }
}