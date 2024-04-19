package settings.data

import settings.domain.KMPPreference
import settings.domain.PreferencesRepo

class PreferenceRepoImpl(private val localPreferences: KMPPreference) : PreferencesRepo {
    override fun saveDistanceUnit(distanceUnit: String) {
        localPreferences
            .putString(KMPPreference.KEY_DISTANCE_UNIT, distanceUnit)

    }

    override fun saveServiceInterval(distance: String) {
        localPreferences
            .putString(KMPPreference.KEY_SERVICE_INTERVAL, distance)

    }

    override fun saveEnabledNotifications(enabled: Boolean) {
        localPreferences
            .putBool(KMPPreference.KEY_NOTIFICATIONS, enabled)

    }

    override fun saveDefaultBike(bikeName: String) {
        localPreferences
            .putString(KMPPreference.KEY_DEFAULT_BIKE, bikeName)

    }

    override fun getDistanceUnit(): String {
        return localPreferences.getString(KMPPreference.KEY_DISTANCE_UNIT) ?: "KM"
    }

    override fun getServiceInterval(): String {
        return localPreferences.getString(KMPPreference.KEY_SERVICE_INTERVAL) ?: "100"
    }

    override fun areNotificationsEnabled(): Boolean {
        return localPreferences.getBool(KMPPreference.KEY_NOTIFICATIONS, false)
    }

    override fun getDefaultBikeName(): String {
        return localPreferences.getString(KMPPreference.KEY_DEFAULT_BIKE) ?: "No bike"
    }
}