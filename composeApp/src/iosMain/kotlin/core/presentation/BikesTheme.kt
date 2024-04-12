package core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import ui.theme.DarkColors
import ui.theme.LightColors

@Composable
actual fun BikesTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}