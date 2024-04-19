package rides.presentation.addride

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.add_ride_label
import bikeappkmp.composeapp.generated.resources.edit_bike_action
import bikeappkmp.composeapp.generated.resources.icon_dropdown
import bikeappkmp.composeapp.generated.resources.ride_bike_label
import bikeappkmp.composeapp.generated.resources.ride_date_label
import bikeappkmp.composeapp.generated.resources.ride_distance_label
import bikeappkmp.composeapp.generated.resources.ride_duration
import bikeappkmp.composeapp.generated.resources.ride_duration_label
import bikeappkmp.composeapp.generated.resources.ride_title_label
import bikes.presentation.list.components.ActionButton
import cafe.adriel.voyager.core.screen.Screen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import rides.presentation.AddRideViewModel
import rides.presentation.addride.components.CustomDatePicker
import rides.presentation.addride.components.TimeDurationPicker
import settings.presentation.components.CustomTextField
import settings.presentation.components.DropDownField
import settings.presentation.components.Label
import settings.presentation.components.NumericTextField
import util.convertMillisToDateMonthNumber

@OptIn(ExperimentalResourceApi::class)
data class AddRideScreen(
    val rideId: Int = 0,
    val onAddRide: () -> Unit = {}
) : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel(
            key = "add-ride-screen",
            factory = viewModelFactory { AddRideViewModel() })
        val state by viewModel.state.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Label(
                stringResource(Res.string.ride_title_label),
                false,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            CustomTextField(
                placeHolder = "",
                text = state.rideName,
                onValueChange = {
                    viewModel.onEvent(AddRideEvent.OnRideTitleAdded(it))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                isError = state.rideNameError != null,
                errorMessage = state.rideNameError,
            )
            Label(
                stringResource(Res.string.ride_bike_label),
                true,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            DropDownField(
                fieldItems = state.bikeNamesList.map { it.name },
                selectedItem = state.bikeName,
                onSelectedItem = { selectedItem ->
                    viewModel.onEvent(AddRideEvent.OnBikeSelected(selectedItem))
                },
                Res.drawable.icon_dropdown,
                modifier = Modifier.fillMaxWidth()
            )
            Label(
                stringResource(Res.string.ride_distance_label),
                true,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            NumericTextField(
                value = state.distance,
                isError = state.distanceError != null,
                errorMessage = state.distanceError,
                defaultSuffix = state.defaultUnit,
                onServiceReminderAdded = { rideDistance ->
                    viewModel.onEvent(AddRideEvent.OnRideDistanceAdded(rideDistance))
                }, modifier = Modifier.padding(bottom = 16.dp)
            )
            Label(
                stringResource(Res.string.ride_duration_label),
                true,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            CustomTextField(
                placeHolder = "",
                text = stringResource(
                    Res.string.ride_duration,
                    state.durationHours,
                    state.durationMinutes
                ),
                onValueChange = {
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.onEvent(AddRideEvent.OnDurationClicked) },
                singleLine = true,
            )
            Label(
                stringResource(Res.string.ride_date_label),
                true,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            CustomTextField(
                placeHolder = "",
                text = convertMillisToDateMonthNumber(state.date),
                onValueChange = {
                },
                keyboardType = KeyboardType.Email,
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.onEvent(AddRideEvent.OnDateClicked) },
                singleLine = true,
            )
            Spacer(modifier = Modifier.weight(1f))
            ActionButton(

                text = if (rideId > 0) {
                    stringResource(Res.string.edit_bike_action)
                } else {
                    stringResource(Res.string.add_ride_label)
                },
                onButtonClick = {
                    viewModel.onEvent(AddRideEvent.Submit)
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        if (state.showDurationPicker) {
            TimeDurationPicker(
                hoursValue = state.durationHours,
                minutesValue = state.durationMinutes,
                onDismissRequest = { viewModel.onEvent(AddRideEvent.OnDismissDurationPicker) },
                onConfirmation = { hours, minutes ->
                    viewModel.onEvent(AddRideEvent.OnDurationSet(hours, minutes))
                })
        }

        if (state.showDatePicker) {
            CustomDatePicker(onDateSelected = { date ->
                viewModel.onEvent(AddRideEvent.OnDateSet(date))
            }, onDismissDialog = {
                viewModel.onEvent(AddRideEvent.OnDismissDatePicker)
            })
        }

        if (state.isValidatedSuccessfully) {
            onAddRide()
//            val inputData = Data.Builder().putString(Constants.BIKE_NAME_KEY, state.value.bikeName)
//                .putString(Constants.RIDE_DISTANCE, state.value.distance).build()
//            val defaultBikeRequest =
//                OneTimeWorkRequestBuilder<BikeServiceWorker>().setInputData(inputData)
//                    .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST).build()
//            WorkManager.getInstance(context).cancelAllWork()
//            WorkManager.getInstance(context).enqueue(defaultBikeRequest)
        }
    }
}