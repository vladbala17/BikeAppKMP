package settings.presentation

data class SettingsState(
    val distanceUnit: String = "Km",
    val serviceIntervalReminder: String = "100",
    val isServiceNotifyEnabled: Boolean = false,
    val defaultBike: String = "TestBike",
    val defaultBikeList: List<String> = emptyList(),
    val showPermissionRationale: Boolean = false
)