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
import bikes.domain.BikesDataSource
import bikes.presentation.list.components.ConfirmationDialog
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.resources.ExperimentalResourceApi
import rides.domain.RidesDataSource
import rides.domain.model.Ride
import rides.domain.use_case.DeleteRide
import rides.domain.use_case.GetRides
import rides.presentation.addride.AddRideScreen
import rides.presentation.detail.RideDetailScreen
import rides.presentation.detail.components.BarChart
import rides.presentation.list.components.RideListItem
import settings.domain.PreferencesRepo

@OptIn(ExperimentalResourceApi::class)
data class RidesScreen(
    val ridesRepository: RidesDataSource,
    val bikesRepository: BikesDataSource,
    val localPreferencesRepo: PreferencesRepo
) : Screen {

    private val ridesUseCases =
        GetRidesUseCases(GetRides(ridesRepository), DeleteRide(ridesRepository))

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getViewModel(
            key = "rides-screen",
            factory = viewModelFactory { RidesViewModel(ridesUseCases) })
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
                    onButtonClick = {
                        navigator.push(
                            AddRideScreen(
                                bikesDataSource = bikesRepository,
                                ridesDataSource = ridesRepository,
                                preferencesRepo = localPreferencesRepo
                            )
                        )
                    },
                )
            } else {
                BarChart(inputData = state.rideStatistic, totalKm = state.totalKm)

                state.rides.forEach { (month, ridesForMonth) ->
                    Text(text = month)
                    ridesForMonth.forEach { ride: Ride ->
                        RideListItem(
                            ride = ride,
                            modifier = Modifier.padding(8.dp),
                            onEditRideMenuClick = { navigator.push(RideDetailScreen(ride.id)) },
                            onDeleteRide = {
                                viewModel.onEvent(RidesEvent.OnDeleteRide(ride.rideName))
                            },
                            onRideItemClick = {
                                navigator.push(RideDetailScreen(rideId = ride.id))
                            }
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