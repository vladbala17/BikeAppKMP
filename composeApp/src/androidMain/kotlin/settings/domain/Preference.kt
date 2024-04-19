package settings.domain

const val SHARED_PREFS_NAME = "bike_kmp_prefs"
actual fun KMPContext.putInt(key: String, value: Int) {
    getSharedPrefsEditor().putInt(key, value).apply()
}
actual fun KMPContext.getInt(key: String, default: Int): Int {
    return  getSharedPrefs().getInt(key, default )
}

actual fun KMPContext.putString(key: String, value: String) {
    getSharedPrefsEditor().putString(key, value).apply()
}

actual fun KMPContext.getString(key: String): String? {
    return  getSharedPrefs().getString(key, null)
}

actual fun KMPContext.putBool(key: String, value: Boolean) {
    getSharedPrefsEditor().putBoolean(key, value).apply()
}

actual fun KMPContext.getBool(key: String, default: Boolean): Boolean {
    return getSharedPrefs().getBoolean(key, default)
}
private fun KMPContext.getSharedPrefs() = getSharedPreferences(SHARED_PREFS_NAME, 0)
private fun KMPContext.getSharedPrefsEditor() = getSharedPrefs().edit()