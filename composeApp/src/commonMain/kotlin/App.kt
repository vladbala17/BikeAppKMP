@file:OptIn(ExperimentalResourceApi::class)

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import bikes.presentation.list.BikesScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import core.presentation.BikesTheme
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import navigation.BOTTOM_NAV_ITEM_LIST
import navigation.BottomNavItem
import navigation.MainScreenViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import rides.presentation.list.RidesScreen
import ui.theme.DarkColors
import ui.theme.LightColors


@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    BikesTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {


        val viewModel =
            getViewModel(key = "main-screen", factory = viewModelFactory { MainScreenViewModel() })

        val state by viewModel.state.collectAsState()

        Navigator(BikesScreen()) { navigator ->
            Scaffold(
                topBar = {

                },
                bottomBar = {
                    CustomBottomNavigationBar(
                        darkTheme,
                        navigator,
                        items = BOTTOM_NAV_ITEM_LIST,
                        onItemClick = {
                            navigator.push(it)
                        })
                }
            ) {
                Box(
                    modifier = Modifier.padding(it)
                ) {
                    CurrentScreen()
                }
            }
        }
    }

}

@Composable
fun CustomBottomNavigationBar(
    darkTheme: Boolean,
    navigator: Navigator,
    items: List<BottomNavItem>,
    onItemClick: (Screen) -> Unit

) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 5.dp,
        containerColor = if (darkTheme) {
            DarkColors.secondaryContainer
        } else {
            LightColors.onPrimaryContainer
        }
    ) {
        items.forEach { destination ->
            val selected = destination.route == navigator.lastItem
            NavigationBarItem(
                selected = selected,
                onClick = {
                    onItemClick(destination.route)
                },
                icon = {
                    if (selected) {
                        Icon(
                            painter = painterResource(destination.selectedIcon),
                            contentDescription = stringResource(destination.titleId),
                            tint = Color.Blue
                        )
                    } else {
                        Icon(
                            painter = painterResource(destination.unselectedIcon),
                            contentDescription = stringResource(destination.titleId),
                            tint = Color.White
                        )
                    }
                },
                label = {
                    Text(
                        text = stringResource(destination.titleId),
                        color = if (selected) {
                            Color.Blue
                        } else {
                            Color.White
                        },
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }

}

@Composable
fun NavigationScreen() {
    Navigator(screen = RidesScreen()) { navigator: Navigator ->
        CurrentScreen()

    }
}