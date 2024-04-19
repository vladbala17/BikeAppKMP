package bikes.presentation.list

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class BikesViewModel : ViewModel() {
    private val _state = MutableStateFlow(BikesState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), BikesState())


    fun onEvent(event: BikesEvent) {

    }
}