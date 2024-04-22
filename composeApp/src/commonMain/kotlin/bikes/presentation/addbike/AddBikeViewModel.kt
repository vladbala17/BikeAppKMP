package bikes.presentation.addbike

import bikes.domain.model.Bike
import bikes.presentation.detail.AddBikeUseCases
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import settings.domain.PreferencesRepo

@OptIn(ExperimentalResourceApi::class)
class AddBikeViewModel(
    val bikeId: Int,
    private val addBikeUseCases: AddBikeUseCases,
    private val preferencesRepo: PreferencesRepo
) : ViewModel() {
    private val _state = MutableStateFlow(AddBikeState())
    val state =
        _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), AddBikeState())

    init {
        if (bikeId > 0) {
            viewModelScope.launch {
                val bike = addBikeUseCases.getBikeDetail(bikeId)
                loadBike(bike)
            }
        }
    }
    fun onEvent(event: AddBikeEvent) {
        when (event) {
            is AddBikeEvent.Submit -> {
                if (bikeNameIsValid() && isServiceDistanceValid()) {
                    _state.update { newState ->
                        newState.copy(isValidatedSuccessfully = true, bikeNameError = null)
                    }

                    val bike = Bike(
                        id = bikeId,
                        name = _state.value.bikeName,
                        wheelSize = _state.value.wheelSize,
                        serviceIn = _state.value.serviceIn.toInt(),
                        isDefault = _state.value.isDefault,
                        bikeType = _state.value.bikePagerList[_state.value.selectedBike].type,
                        bikeColor = _state.value.bikePagerList[_state.value.selectedBike].color
                    )
                    viewModelScope.launch {
                        addBikeUseCases.addBike(bike)
                    }

                }


            }

            is AddBikeEvent.OnColorPick -> {
                _state.update { newState ->
                    val list = _state.value.bikePagerList.toMutableList()
                    list[_state.value.selectedBike] =
                        list[_state.value.selectedBike].copy(color = event.color)
                    newState.copy(bikePagerList = list)
                }

            }

            is AddBikeEvent.OnPageSelected -> {
                selectTypeFromPage(event.page)
            }

            is AddBikeEvent.OnBikeNameAdded -> {
                _state.update { newState ->
                    newState.copy(bikeName = event.bikeName)
                }
            }

            is AddBikeEvent.OnWheelSizeAdded -> {
                _state.update { newState ->
                    newState.copy(wheelSize = event.wheelSize)
                }
            }

            is AddBikeEvent.OnServiceIntervalAdded -> {
                _state.update { newState ->
                    newState.copy(serviceIn = event.serviceInterval)
                }
            }

            is AddBikeEvent.OnDefaultBikeAdded -> {
                if (_state.value.bikeName.isNotEmpty()) {
                    preferencesRepo.saveDefaultBike(_state.value.bikeName)
                }
                _state.update { newState ->
                    newState.copy(isDefault = event.isDefault)
                }
            }
        }
    }

    private fun bikeNameIsValid(): Boolean {
        val bikeNameValidation = addBikeUseCases.bikeNameUseCase(_state.value.bikeName)
        _state.update { newState ->
            newState.copy(bikeNameError = bikeNameValidation.errorMessage)
        }

        return bikeNameValidation.successful
    }

    private fun isServiceDistanceValid(): Boolean {
        val distanceValidation = addBikeUseCases.validDistance(_state.value.serviceIn)
        _state.update { newState ->
            newState.copy(distanceError = distanceValidation.errorMessage)
        }
        return distanceValidation.successful
    }

    private fun selectTypeFromPage(page: Int) {
        _state.update { newState ->
            newState.copy(bikeTitle = _state.value.bikePagerList[page].title, selectedBike = page)
        }
    }
    private fun loadBike(bike: Bike) {
        _state.update { newState ->
            val list = _state.value.bikePagerList.toMutableList()
            list[_state.value.selectedBike] =
                list[_state.value.selectedBike].copy(color = bike.bikeColor, type = bike.bikeType)
            newState.copy(
                bikeTitle = bike.bikeType.type,
                bikeName = bike.name,
                wheelSize = bike.wheelSize,
                serviceIn = bike.serviceIn.toString(),
                isDefault = bike.isDefault,
                bikeType = bike.bikeType,
                bikeColor = bike.bikeColor,
                bikePagerList = list
            )
        }
    }
}