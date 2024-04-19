package rides.presentation.list

sealed class RidesEvent {
    object OnAddRide : RidesEvent()
    data class OnDeleteRide(val rideName: String) : RidesEvent()
    object OnDeleteConfirmation : RidesEvent()
    object OnDismissDialog : RidesEvent()
}
