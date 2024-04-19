package bikes.presentation.addbike

sealed class AddBikeEvent {
    object Submit : AddBikeEvent()
    data class OnColorPick(val color: Int): AddBikeEvent()
    data class OnPageSelected(val page: Int): AddBikeEvent()
    data class OnBikeNameAdded(val bikeName: String): AddBikeEvent()
    data class OnWheelSizeAdded(val wheelSize: String): AddBikeEvent()
    data class OnServiceIntervalAdded(val serviceInterval: String): AddBikeEvent()
    data class OnDefaultBikeAdded(val isDefault: Boolean): AddBikeEvent()
}