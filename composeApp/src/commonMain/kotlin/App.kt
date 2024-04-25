@file:OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.bikes_title
import bikeappkmp.composeapp.generated.resources.icon_add
import bikeappkmp.composeapp.generated.resources.icon_bikes_inactive
import bikeappkmp.composeapp.generated.resources.icon_more
import bikeappkmp.composeapp.generated.resources.icon_x
import bikeappkmp.composeapp.generated.resources.rides_active
import bikeappkmp.composeapp.generated.resources.rides_inactive
import bikeappkmp.composeapp.generated.resources.rides_title
import bikeappkmp.composeapp.generated.resources.settings_active
import bikeappkmp.composeapp.generated.resources.settings_inactive
import bikeappkmp.composeapp.generated.resources.settings_title
import bikes.presentation.addbike.AddBikeScreen
import bikes.presentation.list.BikesScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import core.presentation.BikesTheme
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import di.AppModule
import navigation.BottomNavItem
import navigation.MainScreenEvent
import navigation.MainScreenState
import navigation.MainScreenViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import rides.presentation.addride.AddRideScreen
import rides.presentation.list.RidesScreen
import settings.presentation.SettingsScreen


@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    appModule: AppModule
) {
    BikesTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {

        val mainScreenViewModel =
            getViewModel(key = "main-screen", factory = viewModelFactory { MainScreenViewModel() })

        val mainScreenState by mainScreenViewModel.state.collectAsState()

        val bottomNavItemList = listOf(
            BottomNavItem(
                route = BikesScreen(appModule.bikesDataSource, appModule.localPreferences),
                selectedIcon = Res.drawable.icon_bikes_inactive,
                unselectedIcon = Res.drawable.icon_bikes_inactive,
                titleId = Res.string.bikes_title
            ),
            BottomNavItem(
                route = RidesScreen(
                    ridesRepository = appModule.ridesDataSource,
                    bikesRepository = appModule.bikesDataSource,
                    localPreferencesRepo = appModule.localPreferences
                ),
                selectedIcon = Res.drawable.rides_active,
                unselectedIcon = Res.drawable.rides_inactive,
                titleId = Res.string.rides_title
            ),
            BottomNavItem(
                route = SettingsScreen(appModule.bikesDataSource, appModule.localPreferences),
                selectedIcon = Res.drawable.settings_active,
                unselectedIcon = Res.drawable.settings_inactive,
                titleId = Res.string.settings_title
            )
        )


        Navigator(BikesScreen(appModule.bikesDataSource, appModule.localPreferences)) { navigator ->
            Scaffold(
                topBar = {
                    CustomTopNavigationBar(state = mainScreenState,
                        onBackArrowClick = {
                            navigator.pop()
                        },
                        onAddItemClick = {
                            if (navigator.lastItem.key == bottomNavItemList[0].route.key) {
                                navigator.push(AddBikeScreen(appModule.bikesDataSource, appModule.localPreferences))
                            } else {
                                navigator.push(
                                    AddRideScreen(
                                        ridesDataSource = appModule.ridesDataSource,
                                        bikesDataSource = appModule.bikesDataSource,
                                        preferencesRepo = appModule.localPreferences
                                    )
                                )
                            }
                        },
                        onCloseClick = {
                            navigator.pop()
                        })
                },
                bottomBar = {
                    if (mainScreenState.showBottomNavigationBar) {
                        CustomBottomNavigationBar(
                            navigator,
                            items = bottomNavItemList,
                            onItemClick = { screen ->
                                navigator.push(screen)
                            })
                    }
                }
            ) {
                Box(
                    modifier = Modifier.padding(it)
                ) {
                    CurrentScreen()
                    mainScreenViewModel.onEvent(MainScreenEvent.OnPageChanged(navigator.lastItem.key))
                }
            }
        }
    }

}

@Composable
fun CustomBottomNavigationBar(
    navigator: Navigator,
    items: List<BottomNavItem>,
    onItemClick: (Screen) -> Unit

) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 5.dp,
    ) {
        items.forEach { destination ->
            val selected = destination.route.key == navigator.lastItem.key
            NavigationBarItem(
                selected = selected,
                onClick = {
                    onItemClick(destination.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(destination.unselectedIcon),
                        contentDescription = stringResource(destination.titleId),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                label = {
                    Text(
                        text = stringResource(destination.titleId),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
            )
        }
    }

}

@ExperimentalMaterial3Api
@Composable
fun CustomTopNavigationBar(
    state: MainScreenState,
    onBackArrowClick: () -> Unit,
    onAddItemClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    if (state.showTopAppBar) {
        TopAppBar(
            title = {
                Text(text = stringResource(state.title))
            },
            actions = {
                if (state.showActionIconAdd) {
                    IconButton(onClick = onAddItemClick) {
                        Icon(
                            painter = painterResource(Res.drawable.icon_add),
                            contentDescription = "Add",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
                if (state.showActionText) {
                    Text(
                        text = stringResource(state.actionText),
                        modifier = Modifier.padding(end = 8.dp),
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                if (state.showActionIconX) {
                    IconButton(onClick = onCloseClick) {
                        Icon(
                            painter = painterResource(Res.drawable.icon_x),
                            contentDescription = "Close",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
                if (state.showMenuIcon) {
                    IconButton(onClick = onCloseClick) {
                        Icon(
                            painter = painterResource(Res.drawable.icon_more),
                            contentDescription = "Edit",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            },
            navigationIcon = {
                if (state.showNavigationIcon) {
                    IconButton(onClick = onBackArrowClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Sharp.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
        )
    }
}