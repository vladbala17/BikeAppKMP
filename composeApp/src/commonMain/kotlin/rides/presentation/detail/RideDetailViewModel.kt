package rides.presentation.detail

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class RideDetailViewModel : ViewModel() {
    private val _state = MutableStateFlow(RideDetailState())
    val state =
        _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), RideDetailState())

    fun onEvent(event: RideDetailEvent) {

    }
}