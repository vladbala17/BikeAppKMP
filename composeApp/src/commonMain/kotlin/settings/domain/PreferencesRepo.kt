package settings.domain

interface PreferencesRepo {
    fun saveDistanceUnit(distanceUnit: String)
    fun saveServiceInterval(distance: String)
    fun saveEnabledNotifications(enabled: Boolean)
    fun saveDefaultBike(bikeName: String)

    fun getDistanceUnit(): String
    fun getServiceInterval(): String
    fun areNotificationsEnabled(): Boolean
    fun getDefaultBikeName(): String

    companion object {
        const val KEY_DISTANCE_UNIT= "unit"
        const val KEY_SERVICE_INTERVAL= "interval"
        const val KEY_NOTIFICATIONS= "notifications"
        const val KEY_DEFAULT_BIKE= "bike"
    }
}