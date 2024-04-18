package settings.presentation

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class SettingsViewModel : ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state =  _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), SettingsState())

    private var getBikesJob: Job? = null

    init {
        loadSettings()
    }

    fun onEvent(event: SettingsEvent) {
//        when (event) {
//            is SettingsEvent.OnDefaultBikeSet -> {
//                _state.update { newState ->
//                    newState.copy(defaultBike = event.bike)
//                }
//                preferences.saveDefaultBike(event.bike)
//            }
//
//            is SettingsEvent.OnDistanceUnitSet -> {
//                _state.update { newState ->
//                    newState.copy(distanceUnit = event.unit)
//                }
//                preferences.saveDistanceUnit(event.unit)
//            }
//
//            is SettingsEvent.OnNotifyReminder -> {
//                _state.update { newState ->
//                    newState.copy(isServiceNotifyEnabled = event.notifyReminder)
//                }
//                preferences.saveEnabledNotifications(_state.value.isServiceNotifyEnabled)
//            }
//
//            is SettingsEvent.OnServiceIntervalReminderSet -> {
//                _state.update { newState ->
//
//                    //Use case not needed because the value is already a number
//                    newState.copy(serviceIntervalReminder = filterOutDigits(event.distanceIntervalReminder.toString()))
//                }
//                preferences.saveServiceInterval(filterOutDigits(event.distanceIntervalReminder.toString()))
//            }
//
//            SettingsEvent.OnShowPermissionDialog -> {
//                _state.update { newState ->
//                    newState.copy(showPermissionDialog = true)
//                }
//            }
//            SettingsEvent.OnDismissPermissionDialog -> {
//                _state.update { newState ->
//                    newState.copy(showPermissionDialog = false)
//                }
//            }
//        }
    }

    private fun loadSettings() {
//        getBikesJob?.cancel()
//        getBikesJob = getBikes.invoke().onEach { bikes: List<Bike> ->
//            val bikeNames = bikes.map { it.name }
//            _state.update {
//                it.copy(
//                    distanceUnit = preferences.getDistanceUnit(),
//                    serviceIntervalReminder = preferences.getServiceInterval(),
//                    isServiceNotifyEnabled = preferences.areNotificationsEnabled(),
//                    defaultBike = preferences.getDefaultBikeName(),
//                    defaultBikeList = bikeNames
//                )
//            }
//        }.launchIn(viewModelScope)
    }
}