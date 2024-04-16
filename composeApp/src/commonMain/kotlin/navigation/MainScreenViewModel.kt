package navigation

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MainScreenViewModel : ViewModel() {
    private val _state = MutableStateFlow(MainScreenState())

    val state =
        _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), MainScreenState())
}