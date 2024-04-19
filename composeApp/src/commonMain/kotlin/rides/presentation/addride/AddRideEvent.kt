package rides.presentation.addride

sealed class AddRideEvent() {
    data class OnRideTitleAdded(val rideTitle: String) : AddRideEvent()
    data class OnBikeSelected(val bikeName: String) : AddRideEvent()
    data class OnRideDistanceAdded(val rideDistance: String) : AddRideEvent()
    data class OnRideDurationAdded(val duration: String) : AddRideEvent()
    data class OnRideDateAdded(val date: Long) : AddRideEvent()
    object OnDurationClicked : AddRideEvent()
    object OnDateClicked : AddRideEvent()
    object OnDismissDurationPicker : AddRideEvent()
    object OnDismissDatePicker : AddRideEvent()
    data class OnDurationSet(val hours: Int, val minutes: Int) : AddRideEvent()
    data class OnDateSet(val date: Long) : AddRideEvent()
    object Submit : AddRideEvent()

}
