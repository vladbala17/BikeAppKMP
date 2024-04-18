package settings.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.icon_dropdown
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DropDownField(
    fieldItems: List<String>,
    selectedItem: String,
    onSelectedItem: (String) -> Unit,
    icon: DrawableResource = Res.drawable.icon_dropdown,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = modifier
    ) {
        CustomTextField(
            placeHolder = selectedItem,
            onValueChange = { newValue ->
                onSelectedItem(newValue)
            },
            readOnly = true,
            trailingIcon = {
                Icon(
                    painter = painterResource(resource = icon),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(all = 16.dp)
                )
            },
            modifier = modifier
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = modifier
        ) {
            fieldItems.forEach { selectedOption ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = selectedOption,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }, onClick = {
                        onSelectedItem(selectedOption)
                        expanded = false
                    },
                    modifier = modifier
                )
            }
        }
    }
}