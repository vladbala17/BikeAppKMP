package settings.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.default_bike_label
import bikeappkmp.composeapp.generated.resources.distance_unit_km
import bikeappkmp.composeapp.generated.resources.distance_unit_miles
import bikeappkmp.composeapp.generated.resources.distance_units_label
import bikeappkmp.composeapp.generated.resources.icon_dropdown
import bikeappkmp.composeapp.generated.resources.service_reminder_label
import bikes.domain.use_case.GetBikes
import cafe.adriel.voyager.core.screen.Screen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import settings.domain.PreferencesRepo
import settings.presentation.components.DefaultSwitch
import settings.presentation.components.DropDownField
import settings.presentation.components.Label
import settings.presentation.components.NumericTextField

@OptIn(ExperimentalResourceApi::class)
object SettingsScreen : Screen {

    @Composable
    override fun Content() {
        val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
        val controller = remember(factory) { factory.createPermissionsController() }
        val getBikesUseCase = koinInject<GetBikes>()
        val userPref = koinInject<PreferencesRepo>()
        val viewModel = getViewModel(
            key = "settings-screen",
            factory = viewModelFactory {
                SettingsViewModel(
                    permissionsController = controller,
                    getBikes = getBikesUseCase,
                    preferences = userPref
                )
            })
        val coroutineScope = rememberCoroutineScope()
        val state by viewModel.state.collectAsState()
        val snackBarHostState = remember { SnackbarHostState() }
        val scaffoldState: ScaffoldState = rememberScaffoldState()
        BindEffect(controller)
        Scaffold(
            snackbarHost = {
                SnackbarHost(snackBarHostState)
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Label(
                    stringResource(Res.string.distance_units_label),
                    true,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                DropDownField(
                    listOf(
                        stringResource(Res.string.distance_unit_km),
                        stringResource(Res.string.distance_unit_miles)
                    ),
                    selectedItem = state.distanceUnit,
                    onSelectedItem = { selectedItem ->
                        viewModel.onEvent(SettingsEvent.OnDistanceUnitSet(selectedItem))
                    },
                    Res.drawable.icon_dropdown,
                    modifier = Modifier.fillMaxWidth()
                )
                Label(
                    stringResource(Res.string.service_reminder_label),
                    false,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NumericTextField(
                        state.serviceIntervalReminder,
                        onServiceReminderAdded = { distanceReminder ->
                            viewModel.onEvent(
                                SettingsEvent.OnServiceIntervalReminderSet(
                                    distanceReminder
                                )
                            )
                        },
                        state.distanceUnit,
                        modifier = Modifier.weight(1f)
                    )
                    DefaultSwitch(
                        state.isServiceNotifyEnabled,
                        onCheckedChange = {
                            coroutineScope.launch {
                                if (controller.isPermissionGranted(Permission.REMOTE_NOTIFICATION)) {
                                    viewModel.onEvent(SettingsEvent.OnNotifyReminder(it))
                                } else {
                                    viewModel.onEvent(SettingsEvent.OnShowPermissionRequest(it))
                                }
                            }

                        },
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Label(
                    stringResource(Res.string.default_bike_label),
                    true,
                    modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
                )
                DropDownField(
                    state.defaultBikeList,
                    selectedItem = state.defaultBike,
                    onSelectedItem = { selectedItem ->
                        viewModel.onEvent(SettingsEvent.OnDefaultBikeSet(selectedItem))
                    },
                    Res.drawable.icon_dropdown,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.weight(1f))
                if (state.showPermissionRationale) {
                    coroutineScope.launch {
                        val result =
                            snackBarHostState.showSnackbar(
                                message = "Notification permission needed to notify you about service reminder",
                                actionLabel = "Allow permission",
                                duration = SnackbarDuration.Short
                            )

                        when (result) {
                            SnackbarResult.Dismissed -> {
                                snackBarHostState.currentSnackbarData?.dismiss()
                            }

                            SnackbarResult.ActionPerformed -> {
                                viewModel.onEvent(SettingsEvent.OnShowPermissionRequest(state.isServiceNotifyEnabled))
                            }
                        }
                    }
                }

            }
        }
    }
}

