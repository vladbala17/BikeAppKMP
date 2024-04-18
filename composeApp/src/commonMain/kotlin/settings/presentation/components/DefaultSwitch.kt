package settings.presentation.components

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DefaultSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Switch(checked = checked, onCheckedChange = { onCheckedChange(it) }, modifier = modifier)
}