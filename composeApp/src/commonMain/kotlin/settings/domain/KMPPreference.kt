package settings.domain

class KMPPreference(private val context: KMPContext) {
    companion object {
        const val KEY_DISTANCE_UNIT= "unit"
        const val KEY_SERVICE_INTERVAL= "interval"
        const val KEY_NOTIFICATIONS= "notifications"
        const val KEY_DEFAULT_BIKE= "bike"
    }

    fun putInt(key: String, value: Int) {
        context.putInt(key, value)
    }

    fun putString(key: String, value: String) {
        context.putString(key, value)
    }

    fun putBool(key: String, value: Boolean) {
        context.putBool(key, value)
    }

    fun getInt(key: String, default: Int): Int = context.getInt(key, default)


    fun getString(key: String): String? = context.getString(key)


    fun getBool(key: String, default: Boolean): Boolean =
        context.getBool(key, default)
}