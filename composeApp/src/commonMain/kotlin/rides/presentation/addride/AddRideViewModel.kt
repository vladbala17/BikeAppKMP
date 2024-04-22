package rides.presentation.addride

import bikes.domain.model.Bike
import bikes.domain.model.BikeType
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rides.domain.model.Ride
import settings.domain.PreferencesRepo

class AddRideViewModel(
    private val rideId: Int = 0,
    private val addRidesUseCases: AddRidesUseCases,
    preferencesRepo: PreferencesRepo
) : ViewModel() {
    private val _state = MutableStateFlow(AddRideState())
    val state =
        _state.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            AddRideState(defaultUnit = preferencesRepo.getDistanceUnit())
        )

    private var getRidesJob: Job? = null

    init {
        loadBikesNames()

        if (rideId > 0) {
            viewModelScope.launch {
                val ride = addRidesUseCases.getRideDetail(rideId = rideId)
                loadRide(ride)
            }
        }
    }

    fun onEvent(event: AddRideEvent) {
        when (event) {
            is AddRideEvent.OnBikeSelected -> {
                _state.update { newState ->
                    val getBikeByName =
                        _state.value.bikeNamesList.find { event.bikeName == it.name }
                    newState.copy(bikeName = event.bikeName, bikeType = getBikeByName!!.bikeType)
                }
            }

            is AddRideEvent.OnRideDateAdded -> {}
            is AddRideEvent.OnRideDistanceAdded -> {
                _state.update { newState ->
                    newState.copy(distance = event.rideDistance)
                }
            }

            is AddRideEvent.OnRideDurationAdded -> {}
            is AddRideEvent.OnRideTitleAdded -> {
                _state.update { newState ->
                    newState.copy(rideName = event.rideTitle)
                }
            }

            AddRideEvent.Submit -> {
                if (rideNameIsValid() && isServiceDistanceValid()) {


                    _state.update { newState ->
                        newState.copy(isValidatedSuccessfully = true, rideNameError = null)
                    }
                    val id = rideId
                    val rideTitle = _state.value.rideName
                    val bikeName = _state.value.bikeName
                    val bikeType = _state.value.bikeType
                    val durationHours = _state.value.durationHours
                    val durationMinutes = _state.value.durationMinutes
                    val distance = _state.value.distance
                    val date = _state.value.date

                    val ride = Ride(
                        rideName = rideTitle,
                        bikeName = bikeName,
                        bikeType = bikeType.type,
                        durationHours = durationHours,
                        durationMinutes = durationMinutes,
                        distance = distance.toInt(),
                        date = date,
                        id = id
                    )

                    viewModelScope.launch {
                        addRidesUseCases.addRide(ride)
                    }
                }
            }

            AddRideEvent.OnDurationClicked -> {
                _state.update { newState ->
                    newState.copy(showDurationPicker = true)
                }
            }

            AddRideEvent.OnDismissDurationPicker -> {
                _state.update { newState ->
                    newState.copy(showDurationPicker = false)
                }
            }

            is AddRideEvent.OnDurationSet -> {
                _state.update { newState ->
                    newState.copy(
                        durationHours = event.hours,
                        durationMinutes = event.minutes,
                        showDurationPicker = false
                    )
                }
            }

            AddRideEvent.OnDateClicked -> {
                _state.update { newState ->
                    newState.copy(showDatePicker = true)
                }
            }

            is AddRideEvent.OnDateSet -> {
                _state.update { newState ->
                    newState.copy(date = event.date, showDatePicker = false)
                }
            }

            AddRideEvent.OnDismissDatePicker -> {
                _state.update { newState ->
                    newState.copy(showDatePicker = false)
                }
            }
        }
    }

    private fun rideNameIsValid(): Boolean {
        val rideNameValidation = addRidesUseCases.rideNameUseCase(_state.value.rideName)
        _state.update { newState ->
            newState.copy(rideNameError = rideNameValidation.errorMessage)
        }

        return rideNameValidation.successful
    }

    private fun isServiceDistanceValid(): Boolean {
        val distanceValidation = addRidesUseCases.validateDistance(_state.value.distance)
        _state.update { newState ->
            newState.copy(distanceError = distanceValidation.errorMessage)
        }
        return distanceValidation.successful
    }

    private fun loadBikesNames() {
        getRidesJob?.cancel()
        getRidesJob = addRidesUseCases.getBikes().onEach { bikes: List<Bike> ->
            _state.update { newState ->
                newState.copy(bikeNamesList = bikes)
            }
        }.launchIn(viewModelScope)
    }

    private fun loadRide(ride: Ride) {
        _state.update { newState ->
            newState.copy(
                rideName = ride.rideName,
                bikeName = ride.bikeName,
                bikeType = BikeType.fromString(ride.bikeType),
                distance = ride.distance.toString(),
                durationHours = ride.durationHours,
                durationMinutes = ride.durationMinutes,
                date = ride.date
            )
        }
    }
}