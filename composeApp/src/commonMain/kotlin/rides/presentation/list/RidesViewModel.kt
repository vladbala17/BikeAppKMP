package rides.presentation.list

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class RidesViewModel : ViewModel() {
    private val _state = MutableStateFlow(RidesState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), RidesState())


    fun onEvent(event: RidesEvent) {

    }
}