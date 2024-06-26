package bikes.presentation.addbike

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.add_bike_label
import bikeappkmp.composeapp.generated.resources.bike_name_label
import bikeappkmp.composeapp.generated.resources.default_bike_label
import bikeappkmp.composeapp.generated.resources.edit_bike_action
import bikeappkmp.composeapp.generated.resources.icon_dropdown
import bikeappkmp.composeapp.generated.resources.service_in_label
import bikeappkmp.composeapp.generated.resources.wheel_size_label
import bikes.presentation.addbike.components.BikesPager
import bikes.presentation.addbike.components.HorizontalColorPicker
import bikes.presentation.detail.AddBikeUseCases
import bikes.presentation.list.components.ActionButton
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import settings.domain.PreferencesRepo
import settings.presentation.components.CustomTextField
import settings.presentation.components.DefaultSwitch
import settings.presentation.components.DropDownField
import settings.presentation.components.Label
import settings.presentation.components.NumericTextField

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
data class AddBikeScreen(
    val bikeId: Int = 0,
    val modifier: Modifier = Modifier,
) : Screen {

    @Composable
    override fun Content() {
        val addBikeUseCases = koinInject<AddBikeUseCases>()
        val preferencesRepo = koinInject<PreferencesRepo>()
        val navigator = LocalNavigator.currentOrThrow

        val viewModel = getViewModel(
            key = "add-bike-screen",
            factory = viewModelFactory {
                AddBikeViewModel(
                    bikeId = bikeId,
                    addBikeUseCases,
                    preferencesRepo
                )
            })
        val state by viewModel.state.collectAsState()

        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            HorizontalColorPicker(
                onColorClicked = { color ->
                    viewModel.onEvent(AddBikeEvent.OnColorPick(color))
                })
            BikesPager(
                bikesList = viewModel.state.value.bikePagerList,
                bikeName = state.bikeTitle,
                onPageChanged = { page ->
                    viewModel.onEvent(AddBikeEvent.OnPageSelected(page))
                })
            Label(
                title = stringResource(Res.string.bike_name_label),
                isMandatory = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp)
            )
            CustomTextField(
                placeHolder = "",
                text = state.bikeName,
                onValueChange = {
                    viewModel.onEvent(AddBikeEvent.OnBikeNameAdded(it))
                },
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 6.dp),
                singleLine = true,
                isError = state.bikeNameError != null,
                errorMessage = state.bikeNameError,
            )
            Label(
                title = stringResource(Res.string.wheel_size_label),
                isMandatory = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp)
            )
            DropDownField(
                listOf(
                    "29'", "30'"
                ),
                selectedItem = state.wheelSize,
                onSelectedItem = { selectedItem ->
                    viewModel.onEvent(AddBikeEvent.OnWheelSizeAdded(selectedItem))
                },
                Res.drawable.icon_dropdown,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 3.dp, end = 3.dp)
            )
            Label(
                title = stringResource(Res.string.service_in_label),
                isMandatory = true,
                modifier = Modifier.padding(start = 6.dp)
            )
            NumericTextField(
                value = state.serviceIn,
                defaultSuffix = state.defaultUnit,
                isError = state.distanceError != null,
                errorMessage = state.distanceError,
                onServiceReminderAdded = { serviceInterval ->
                    viewModel.onEvent(AddBikeEvent.OnServiceIntervalAdded(serviceInterval))
                },
                modifier = Modifier
                    .padding(start = 6.dp, end = 6.dp)
            )
            Row(modifier = Modifier.fillMaxWidth().padding(start = 6.dp, end = 6.dp)) {
                Text(
                    text = stringResource(Res.string.default_bike_label),
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
                DefaultSwitch(
                    checked = state.isDefault,
                    onCheckedChange = { isDefault ->
                        viewModel.onEvent(AddBikeEvent.OnDefaultBikeAdded(isDefault))
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            ActionButton(
                text = if (bikeId > 0) {
                    stringResource(Res.string.edit_bike_action)
                } else {
                    stringResource(Res.string.add_bike_label)
                },
                onButtonClick = {
                    viewModel.onEvent(AddBikeEvent.Submit)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 6.dp)
            )
        }
        if (state.isValidatedSuccessfully) {
            navigator.pop()
        }
    }
}
