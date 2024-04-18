package settings.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.distance_units_label
import bikeappkmp.composeapp.generated.resources.icon_required
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Label(title: String = "", isMandatory: Boolean = false, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        if (isMandatory) {
            Icon(
                painter = painterResource(Res.drawable.icon_required),
                contentDescription = stringResource(Res.string.distance_units_label),
                tint = MaterialTheme.colors.onSecondary,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}