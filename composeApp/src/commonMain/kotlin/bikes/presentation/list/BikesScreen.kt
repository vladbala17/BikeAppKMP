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
import bikes.domain.model.Bike
import bikes.presentation.list.components.BikeListItem
import bikes.presentation.list.components.ConfirmationDialog
import cafe.adriel.voyager.core.screen.Screen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
class BikesScreen(
    val onNavigateToScreen: () -> Unit = {},
    val onEditBike: (Int) -> Unit = {},
    val onBikeClick: (Int) -> Unit = {}
) : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel(
            key = "bikes-screen",
            factory = viewModelFactory { BikesViewModel() })
        val state by viewModel.state.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 8.dp),
        ) {
            if (state.bikes.isEmpty()) {
                EmptyHeader(onButtonClick = {
                    onNavigateToScreen()
                })
            }
            LazyColumn {
                items(items = state.bikes, key = { bike: Bike -> bike.id }) { bike: Bike ->
                    BikeListItem(
                        bike = bike,
                        modifier = Modifier.padding(8.dp),
                        remainingServiceDistance = bike.remainingServiceDistance,
                        usageUntilService = bike.usageUntilService,
                        onEditBikeMenuClick = { onEditBike(bike.id) },
                        defaultUnit = state.defaultUnit,
                        onDeleteBike = {
                            viewModel.onEvent(BikesEvent.OnDeleteBike(bike.name))
                        },
                        onBikeItemClick = onBikeClick
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