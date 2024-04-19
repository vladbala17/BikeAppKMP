package settings.domain

expect fun KMPContext.putInt(key: String, value: Int)
expect fun KMPContext.getInt(key: String, default: Int): Int
expect fun KMPContext.putString(key: String, value: String)
expect fun KMPContext.getString(key: String): String?
expect fun KMPContext.putBool(key: String, value: Boolean)
expect fun KMPContext.getBool(key: String, default: Boolean): Boolean