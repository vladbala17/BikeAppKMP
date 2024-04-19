package rides.presentation

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import rides.presentation.addride.AddRideEvent
import rides.presentation.addride.AddRideState

class AddRideViewModel: ViewModel() {
    private val _state = MutableStateFlow(AddRideState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), AddRideState())

    fun onEvent(event: AddRideEvent) {

    }
}