package bikes.presentation.list

import EmptyHeader
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bikes.domain.BikesDataSource
import bikes.domain.model.Bike
import bikes.domain.use_case.DeleteBike
import bikes.domain.use_case.GetBikes
import bikes.domain.use_case.GetRidesForBike
import bikes.presentation.addbike.AddBikeScreen
import bikes.presentation.detail.BikeDetailScreen
import bikes.presentation.list.components.BikeListItem
import bikes.presentation.list.components.ConfirmationDialog
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.resources.ExperimentalResourceApi
import settings.domain.PreferencesRepo

@OptIn(ExperimentalResourceApi::class)
class BikesScreen(
    private val bikesRepository: BikesDataSource,
    private val bikesPreferencesRepo: PreferencesRepo,
) : Screen {

    private val bikesListUseCases = BikesListUseCases(
        GetBikes(bikesRepository),
        DeleteBike(bikesRepository),
        GetRidesForBike(bikesRepository)
    )

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getViewModel(
            key = "bikes-screen",
            factory = viewModelFactory { BikesViewModel(bikesListUseCases, bikesPreferencesRepo) })
        val state by viewModel.state.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 8.dp),
        ) {
            if (state.bikes.isEmpty()) {
                EmptyHeader(onButtonClick = {
                    navigator.push(AddBikeScreen(bikesRepository, bikesPreferencesRepo))
                })
            }
            LazyColumn {
                items(items = state.bikes, key = { bike: Bike -> bike.id }) { bike: Bike ->
                    BikeListItem(
                        bike = bike,
                        modifier = Modifier.padding(8.dp),
                        remainingServiceDistance = bike.remainingServiceDistance,
                        usageUntilService = bike.usageUntilService,
                        onEditBikeMenuClick = {
                            navigator.push(
                                AddBikeScreen(
                                    bikesRepository,
                                    bikesPreferencesRepo,
                                    bike.id
                                )
                            )
                        },
                        defaultUnit = state.defaultUnit,
                        onDeleteBike = {
                            viewModel.onEvent(BikesEvent.OnDeleteBike(bike.name))
                        },
                        onBikeItemClick = {
                            navigator.push(BikeDetailScreen(bikeId = bike.id))
                        }
                    )
                }
            }

            if (state.showDialog) {
                ConfirmationDialog(
                    onDismissRequest = { viewModel.onEvent(BikesEvent.OnDismissDialog) },
                    onConfirmation = {
                        viewModel.onEvent(BikesEvent.OnDeleteConfirmation)
                    },
                    dialogText = state.bikeName,
                )
            }
        }
    }
}