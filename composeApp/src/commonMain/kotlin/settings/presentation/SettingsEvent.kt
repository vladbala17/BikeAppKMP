package settings.presentation

sealed class SettingsEvent {
    data class OnDistanceUnitSet(val unit: String) : SettingsEvent()
    data class OnServiceIntervalReminderSet(val distanceIntervalReminder: String) : SettingsEvent()
    data class OnNotifyReminder(val notifyReminder: Boolean) : SettingsEvent()
    object OnShowPermissionDialog : SettingsEvent()
    object OnDismissPermissionDialog : SettingsEvent()
    data class OnDefaultBikeSet(val bike: String) : SettingsEvent()
}