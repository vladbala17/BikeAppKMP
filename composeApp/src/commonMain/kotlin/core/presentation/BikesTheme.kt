package core.presentation

import androidx.compose.runtime.Composable
@Composable
expect fun BikesTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)