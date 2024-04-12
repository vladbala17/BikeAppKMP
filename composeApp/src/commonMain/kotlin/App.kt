import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.add_bike_label
import bikeappkmp.composeapp.generated.resources.bike_electric_big_wheels
import core.presentation.BikesTheme
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


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
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            var showContent by remember { mutableStateOf(false) }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { showContent = !showContent }) {
                    Text(stringResource(Res.string.add_bike_label))
                }
                AnimatedVisibility(showContent) {
                    val greeting = remember { Greeting().greet() }
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painterResource(Res.drawable.bike_electric_big_wheels), null)

                        Text("Compose: $greeting")
                    }
                }
            }
        }
    }
}