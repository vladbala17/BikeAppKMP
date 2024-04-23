package rides.presentation.list

import bikes.domain.model.BikeType
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rides.domain.model.Ride
import rides.domain.model.RideChartRow
import util.convertMillisToDateMonthNumber
import util.convertMonthNumberToMonthName

class RidesViewModel(val getRidesUseCases: GetRidesUseCases) : ViewModel() {
    private val _state = MutableStateFlow(RidesState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), RidesState())

    private var getRidesJob: Job? = null

    init {
        loadRides()
    }

    fun onEvent(event: RidesEvent) {
        when (event) {
            RidesEvent.OnAddRide -> {}
            RidesEvent.OnDeleteConfirmation -> {
                viewModelScope.launch {
                    getRidesUseCases.deleteRide(_state.value.rideName)
                    _state.update { newState ->
                        newState.copy(showDialog = false)
                    }
                }
            }

            is RidesEvent.OnDeleteRide -> {
                _state.update { newState ->
                    newState.copy(rideName = event.rideName, showDialog = true)
                }
            }

            RidesEvent.OnDismissDialog -> {
                _state.update { newState ->
                    newState.copy(showDialog = false)
                }
            }
        }
    }

    private fun loadRides() {
        getRidesJob?.cancel()
        getRidesJob = getRidesUseCases.getRides().onEach { rides: List<Ride> ->
            withContext(Dispatchers.IO) {

                val groupedList = rides.groupBy {
                    val dateString = convertMillisToDateMonthNumber(it.date)
                    val monthNumber = dateString.substring(3, dateString.lastIndexOf(".")).toInt()

                    convertMonthNumberToMonthName(monthNumber)
                }

                val totalKm = rides.sumOf { it.distance }
                val rideStatistic = rides.groupBy {
                    it.bikeType
                }

                val chartRows = rideStatistic.map { it ->
                    RideChartRow(
                        BikeType.fromString(it.key),
                        it.value.sumOf { it.distance })
                }

                _state.update { newState ->
                    newState.copy(rides = groupedList, totalKm = totalKm, rideStatistic = chartRows)
                }
            }
        }.launchIn(viewModelScope)

    }
}