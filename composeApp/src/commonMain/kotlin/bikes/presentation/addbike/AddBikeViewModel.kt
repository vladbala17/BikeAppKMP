package bikes.presentation.addbike

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class AddBikeViewModel : ViewModel() {
    private val _state = MutableStateFlow(AddBikeState())
    val state =
        _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), AddBikeState())

    fun onEvent(event: AddBikeEvent) {

    }
}