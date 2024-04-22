package bikes.presentation.list

import bikes.domain.model.Bike
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import settings.domain.PreferencesRepo

class BikesViewModel(
    private val bikesListUseCases: BikesListUseCases,
    private val preferencesRepo: PreferencesRepo
) : ViewModel() {
    private val _state = MutableStateFlow(BikesState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), BikesState())

    private var getBikesJob: Job? = null

    init {
        loadBikes()
    }

    fun onEvent(event: BikesEvent) {
        when (event) {
            BikesEvent.OnAddBike -> {

            }

            is BikesEvent.OnDeleteBike -> {
                _state.update { newState ->
                    newState.copy(bikeName = event.bikeName, showDialog = true)
                }
            }

            BikesEvent.OnDeleteConfirmation -> {
                viewModelScope.launch {
                    bikesListUseCases.deleteBike(_state.value.bikeName)
                    _state.update { newState ->
                        newState.copy(showDialog = false)
                    }
                }
            }

            BikesEvent.OnDismissDialog -> {
                _state.update { newState ->
                    newState.copy(showDialog = false)
                }
            }
        }
    }

    private fun loadBikes() {
        getBikesJob?.cancel()
        getBikesJob = bikesListUseCases.getBikes().onEach { bikes: List<Bike> ->
            val bikeRidesList = bikes.map { bike: Bike ->
                val rideList = bikesListUseCases.getRidesForBike(bike.name)
                val totalDistance = rideList.sumOf { it.distance }
                bike.copy(
                    remainingServiceDistance = bike.serviceIn - totalDistance,
                    usageUntilService = totalDistance.toFloat() / bike.serviceIn.toFloat()
                )
            }
            _state.update { newState ->
                newState.copy(
                    bikes = bikeRidesList,
                    defaultUnit = preferencesRepo.getDistanceUnit()
                )
            }
        }.launchIn(viewModelScope)
    }
}