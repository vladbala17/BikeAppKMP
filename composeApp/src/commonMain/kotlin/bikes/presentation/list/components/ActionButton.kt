package bikes.presentation.list.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ActionButton(
    text: String = "Add bike",
    onButtonClick: () -> Unit = {},

    modifier: Modifier = Modifier
) {
    Button(onClick = onButtonClick, modifier = modifier) {
        Text(
            text = text,
        )
    }
}