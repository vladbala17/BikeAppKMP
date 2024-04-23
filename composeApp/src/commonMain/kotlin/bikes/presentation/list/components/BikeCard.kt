package bikes.presentation.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.icon_more
import bikeappkmp.composeapp.generated.resources.service_in_label
import bikeappkmp.composeapp.generated.resources.wheels_label
import bikes.domain.model.BikeType
import bikes.presentation.addbike.components.BikeFactory
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.theme.OceanBlue

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BikeCard(
    bikeId: Int = 0,
    bikeName: String = "Nukeproof Scout 290",
    wheelSize: String = "29'",
    remainingServiceDistance: Int = 0,
    bikeType: BikeType = BikeType.Electric,
    bikeColor: Color = Color.DarkGray,
    usageUntilService: Float = 0.8f,
    defaultUnit: String = "",
    onEditBikeMenuClick: (Int) -> Unit = {},
    onDeleteBikeMenuClick: (String) -> Unit = {},
    onBikeCardClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(backgroundColor = OceanBlue, modifier = modifier) {
        var displayMenu by remember { mutableStateOf(false) }
        Column(modifier = Modifier
            .padding(16.dp)
            .clickable { onBikeCardClick(bikeId) }) {
            Box(
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                IconButton(
                    onClick = {
                        displayMenu = !displayMenu
                    }) {
                    Icon(
                        painter = painterResource(Res.drawable.icon_more),
                        contentDescription = "bike card menu",
                        tint = MaterialTheme.colorScheme.inversePrimary
                    )
                }
                CardDropDownMenu(
                    displayMenu = displayMenu,
                    onDismissRequest = { displayMenu = false },
                    itemId = bikeId,
                    onEditItemClick = onEditBikeMenuClick,
                    onDeleteItemClick = onDeleteBikeMenuClick
                )
            }
            BikeFactory(
                modifier = Modifier.fillMaxWidth(),
                bodyType = bikeType,
                bodyColor = bikeColor
            )
            Text(
                text = bikeName,
                color = MaterialTheme.colorScheme.inversePrimary,
                style = MaterialTheme.typography.headlineSmall
            )
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = stringResource(Res.string.wheels_label),
                    color = MaterialTheme.colorScheme.inversePrimary,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = wheelSize,
                    color = MaterialTheme.colorScheme.inversePrimary,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
                Text(
                    text = stringResource(Res.string.service_in_label) + " ",
                    color = MaterialTheme.colorScheme.inversePrimary,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "$remainingServiceDistance $defaultUnit",
                    color = MaterialTheme.colorScheme.inversePrimary,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            LinearProgressBar(
                progress = usageUntilService
            )
        }
    }
}