package bikes.presentation.detail

import androidx.compose.ui.graphics.Color
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BikeDetailViewModel(
    val bikeId: Int = 0,
    private val bikeDetailUseCases: BikeDetailUseCases
) :
    ViewModel() {
    private val _state = MutableStateFlow(BikeDetailState())
    val state = _state.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000L),
        BikeDetailState()
    )

    init {
        if (bikeId > 0) {
            viewModelScope.launch {
                val bike = bikeDetailUseCases.getBikeDetail(bikeId)
                val rideList = bikeDetailUseCases.getRidesForBike(bike.name)
                val totalDistance = rideList.sumOf { ride -> ride.distance.toInt() }
                _state.update { newState ->
                    newState.copy(
                        bikeColor = Color(bike.bikeColor),
                        bikeName = bike.name,
                        wheelSize = bike.wheelSize,
                        bikeType = bike.bikeType,
                        rideList = rideList,
                        totalRides = rideList.size,
                        remainingServiceKm = bike.serviceIn - totalDistance,
                        totalRidesDistance = totalDistance,
                        usageUntilService = totalDistance.toFloat() / bike.serviceIn.toFloat()
                    )
                }
            }

        }
    }
}