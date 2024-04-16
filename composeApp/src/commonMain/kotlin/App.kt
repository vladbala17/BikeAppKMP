import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import core.presentation.BikesTheme
import navigation.BottomNavItem
import org.jetbrains.compose.resources.ExperimentalResourceApi


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
        Scaffold(
            topBar = {

            },
            bottomBar = {

            }
        ) {

        }
    }
}

@Composable
fun CustomBottomNavigationBar(
    items: List<BottomNavItem>,
) {
    val navigator = LocalNavigator.currentOrThrow
    NavigationBar(modifier = Modifier.fillMaxWidth(), tonalElevation = 5.dp) {
        items.forEach { destination ->
            NavigationBarItem(
                selected = true,
                onClick = {},
                icon = {

                }
            )
        }
    }

}