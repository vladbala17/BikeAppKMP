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
import bikes.domain.BikesDataSource
import bikes.domain.use_case.AddBike
import bikes.domain.use_case.GetBikeDetail
import bikes.domain.use_case.ValidateBikeName
import bikes.domain.use_case.ValidateDistance
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
import settings.domain.PreferencesRepo
import settings.presentation.components.CustomTextField
import settings.presentation.components.DefaultSwitch
import settings.presentation.components.DropDownField
import settings.presentation.components.Label
import settings.presentation.components.NumericTextField

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
data class AddBikeScreen(
    val bikesDataSource: BikesDataSource,
    val preferencesRepo: PreferencesRepo,
    val bikeId: Int = 0,
    val modifier: Modifier = Modifier,
) : Screen {

    private val addBikeUseCases = AddBikeUseCases(
        AddBike(bikesDataSource),
        GetBikeDetail(bikesDataSource),
        ValidateBikeName(),
        ValidateDistance()
    )


    @Composable
    override fun Content() {
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
                .fillMaxSize()
                .padding(4.dp),
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
                modifier = Modifier.fillMaxWidth()
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
                    .fillMaxWidth(),
                singleLine = true,
                isError = state.bikeNameError != null,
                errorMessage = state.bikeNameError,
            )
            Label(title = stringResource(Res.string.wheel_size_label), isMandatory = true)
            DropDownField(
                listOf(
                    "29'", "30'"
                ),
                selectedItem = state.wheelSize,
                onSelectedItem = { selectedItem ->
                    viewModel.onEvent(AddBikeEvent.OnWheelSizeAdded(selectedItem))
                },
                Res.drawable.icon_dropdown,
                modifier = Modifier.fillMaxWidth()
            )
            Label(title = stringResource(Res.string.service_in_label), isMandatory = true)
            NumericTextField(
                value = state.serviceIn,
                defaultSuffix = state.defaultUnit,
                isError = state.distanceError != null,
                errorMessage = state.distanceError,
                onServiceReminderAdded = { serviceInterval ->
                    viewModel.onEvent(AddBikeEvent.OnServiceIntervalAdded(serviceInterval))
                })
            Row(modifier = Modifier.fillMaxWidth()) {
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
            )
        }
        if (state.isValidatedSuccessfully) {
            navigator.pop()
        }
    }
}
