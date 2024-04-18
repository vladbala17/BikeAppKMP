package bikes.presentation.list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.delete_menu_action
import bikeappkmp.composeapp.generated.resources.edit_menu_action
import bikeappkmp.composeapp.generated.resources.icon_delete
import bikeappkmp.composeapp.generated.resources.icon_edit
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun CardDropDownMenu(
    displayMenu: Boolean = false,
    modifier: Modifier = Modifier,
    itemId: Int = 0,
    itemName: String = "",
    onDismissRequest: () -> Unit = {},
    onEditItemClick: (Int) -> Unit = {},
    onDeleteItemClick: (String) -> Unit = {}
) {
    DropdownMenu(expanded = displayMenu, onDismissRequest = onDismissRequest) {
        DropdownMenuItem(
            text = {
                Icon(
                    painter = painterResource(Res.drawable.icon_edit),
                    contentDescription = "card menu edit",
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(text = stringResource(Res.string.edit_menu_action))
            },
            onClick = { onEditItemClick(itemId) })
        DropdownMenuItem(
            text = {
                Icon(
                    painter = painterResource(Res.drawable.icon_delete),
                    contentDescription = "card menu delete",
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(text = stringResource(Res.string.delete_menu_action))
            },
            onClick = { onDeleteItemClick(itemName) })
    }
}