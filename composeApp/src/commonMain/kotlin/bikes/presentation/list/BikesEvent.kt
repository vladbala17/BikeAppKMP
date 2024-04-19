package bikes.presentation.list

sealed class BikesEvent {
    object OnAddBike : BikesEvent()
    data class OnDeleteBike(val bikeName: String) : BikesEvent()
    object OnDeleteConfirmation : BikesEvent()
    object OnDismissDialog : BikesEvent()
}