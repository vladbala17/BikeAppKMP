@file:OptIn(ExperimentalResourceApi::class)

package navigation

import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.add_bike_label
import bikeappkmp.composeapp.generated.resources.add_ride_label
import bikeappkmp.composeapp.generated.resources.bikes_title
import bikeappkmp.composeapp.generated.resources.empty_string
import bikeappkmp.composeapp.generated.resources.rides_title
import bikeappkmp.composeapp.generated.resources.settings_title
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
class MainScreenViewModel : ViewModel() {
    private val _state = MutableStateFlow(MainScreenState())

    val state =
        _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), MainScreenState())


    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.OnPageChanged -> {
                processPage(event.pageTitle)
            }
        }
    }


    private fun processPage(pageTitle: String) {
        when (pageTitle) {
            Route.BIKES -> {
                _state.update { oldState ->
                    oldState.copy(
                        title = Res.string.bikes_title,
                        showNavigationIcon = false,
                        showBottomNavigationBar = true,
                        showActionIconAdd = true,
                        showActionText = true,
                        actionText = Res.string.add_bike_label,
                        showActionIconX = false,
                        showMenuIcon = false,
                        showTopAppBar = true
                    )
                }
            }

            Route.ADD_BIKES -> {
                _state.update { oldState ->
                    oldState.copy(
                        title = Res.string.add_bike_label,
                        showNavigationIcon = false,
                        showBottomNavigationBar = false,
                        showActionIconAdd = false,
                        showActionText = false,
                        showActionIconX = true
                    )
                }
            }

            Route.BIKE_DETAIL -> {
                _state.update { oldState ->
                    oldState.copy(
                        title = Res.string.empty_string,
                        showNavigationIcon = true,
                        showBottomNavigationBar = false,
                        showActionIconAdd = false,
                        showActionText = false,
                        actionText = Res.string.add_ride_label,
                        showActionIconX = false,
                        showMenuIcon = true,
                        showTopAppBar = false
                    )
                }
            }
            Route.RIDES -> {
                _state.update { oldState ->
                    oldState.copy(
                        title = Res.string.rides_title,
                        showNavigationIcon = false,
                        showBottomNavigationBar = true,
                        showActionIconAdd = true,
                        showActionText = true,
                        actionText = Res.string.add_ride_label,
                        showActionIconX = false
                    )
                }
            }

            Route.RIDE_DETAIL -> {}
            Route.ADD_RIDES -> {
                _state.update { oldState ->
                    oldState.copy(
                        title = Res.string.add_ride_label,
                        showNavigationIcon = false,
                        showBottomNavigationBar = false,
                        showActionIconAdd = false,
                        showActionText = false,
                        showActionIconX = true
                    )
                }
            }

            Route.SETTINGS -> {
                _state.update { oldState ->
                    oldState.copy(
                        title = Res.string.settings_title,
                        showNavigationIcon = true,
                        showBottomNavigationBar = true,
                        actionText = Res.string.empty_string,
                        showActionIconAdd = false
                    )
                }
            }
        }
    }
}