package settings.presentation

import bikes.domain.model.Bike
import bikes.domain.use_case.GetBikes
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import settings.domain.PreferencesRepo

class SettingsViewModel(
    val permissionsController: PermissionsController,
    private val getBikes: GetBikes,
    private val preferences: PreferencesRepo
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state =
        _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), SettingsState())

    private var getBikesJob: Job? = null

    init {
        loadSettings()
        _state.update {
            it.copy(
                distanceUnit = preferences.getDistanceUnit(),
                serviceIntervalReminder = preferences.getServiceInterval(),
                isServiceNotifyEnabled = preferences.areNotificationsEnabled(),
                defaultBike = preferences.getDefaultBikeName(),
            )
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnDefaultBikeSet -> {
                _state.update { newState ->
                    newState.copy(defaultBike = event.bike)
                }
                preferences.saveDefaultBike(event.bike)
            }

            is SettingsEvent.OnDistanceUnitSet -> {
                _state.update { newState ->
                    newState.copy(distanceUnit = event.unit)
                }
                preferences.saveDistanceUnit(event.unit)
            }

            is SettingsEvent.OnNotifyReminder -> {
                _state.update { newState ->
                    newState.copy(isServiceNotifyEnabled = event.notifyReminder)
                }
                preferences.saveEnabledNotifications(_state.value.isServiceNotifyEnabled)
            }

            is SettingsEvent.OnServiceIntervalReminderSet -> {
                _state.update { newState ->

                    //Use case not needed because the value is already a number
                    newState.copy(serviceIntervalReminder = event.distanceIntervalReminder)
                }
                preferences.saveServiceInterval(event.distanceIntervalReminder)
            }

            SettingsEvent.OnShowPermissionRationale -> {
                _state.update { newState ->
                    newState.copy(showPermissionRationale = true)
                }
            }

            SettingsEvent.OnDismissPermissionDialog -> {
                _state.update { newState ->
                    newState.copy(showPermissionRationale = false)
                }
            }

            is SettingsEvent.OnShowPermissionRequest -> {
                onRequestPermission(event, Permission.REMOTE_NOTIFICATION)
            }
        }
    }

    private fun loadSettings() {
        getBikesJob?.cancel()
        getBikesJob = getBikes().onEach { bikes: List<Bike> ->
            val bikeNames = bikes.map { it.name }
            _state.update {
                it.copy(
                    distanceUnit = preferences.getDistanceUnit(),
                    serviceIntervalReminder = preferences.getServiceInterval(),
                    isServiceNotifyEnabled = preferences.areNotificationsEnabled(),
                    defaultBike = preferences.getDefaultBikeName(),
                    defaultBikeList = bikeNames
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun onRequestPermission(
        event: SettingsEvent.OnShowPermissionRequest,
        permission: Permission
    ) {
        viewModelScope.launch {
            if (permissionsController.isPermissionGranted(permission).not()) {
                requestPermission(event, Permission.REMOTE_NOTIFICATION)
            } else {
                _state.update { newState ->
                    newState.copy(isServiceNotifyEnabled = event.notifyReminder, showPermissionRationale = false)
                }
                preferences.saveEnabledNotifications(_state.value.isServiceNotifyEnabled)
            }
        }
    }

    private fun requestPermission(
        event: SettingsEvent.OnShowPermissionRequest,
        permission: Permission
    ) {
        viewModelScope.launch {
            try {
                // Calls suspend function in a coroutine to request some permission.
                permissionsController.providePermission(permission)
                // If there are no exceptions, permission has been granted successfully.
                _state.update { newState ->
                    newState.copy(isServiceNotifyEnabled = event.notifyReminder, showPermissionRationale = false)
                }
                preferences.saveEnabledNotifications(_state.value.isServiceNotifyEnabled)
            } catch (deniedAlwaysException: DeniedAlwaysException) {
                _state.update { newState ->
                    newState.copy(showPermissionRationale = true)
                }
            } catch (deniedException: DeniedException) {
                _state.update { newState ->
                    newState.copy(showPermissionRationale = true)
                }
            } catch (exception: IllegalStateException) {
                _state.update { newState ->
                    newState.copy(showPermissionRationale = true)
                }
            }

        }
    }


}