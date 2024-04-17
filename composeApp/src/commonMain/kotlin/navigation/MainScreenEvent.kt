package navigation

sealed class MainScreenEvent {
    data class OnPageChanged(val pageTitle: String = "Bikes"): MainScreenEvent()
}