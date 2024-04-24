package rides.presentation.addride.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.cancel_dialog_action
import bikeappkmp.composeapp.generated.resources.set_dialog_action
import bikes.presentation.list.components.ActionButton
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TimeDurationPicker(
    hoursValue: Int = 3,
    minutesValue: Int = 25,
    onDismissRequest: () -> Unit = {},
    onConfirmation: (Int, Int) -> Unit = { hours, mins -> }
) {
    var hourValue by remember {
        mutableStateOf(hoursValue)
    }
    var minValue by remember {
        mutableStateOf(minutesValue)
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    VerticalPager(hoursList) {
                        hourValue = it
                    }
                    Text(
                        text = "Hours",
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                    )
                    VerticalPager(minutesList) {
                        minValue = it
                    }
                    Text(
                        text = "Minutes",
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    ActionButton(
                        onButtonClick = { onDismissRequest() },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(8.dp),
                        text = stringResource(Res.string.cancel_dialog_action)
                    )
                    ActionButton(
                        text = stringResource(Res.string.set_dialog_action),
                        onButtonClick = { onConfirmation(hourValue, minValue) },
                        modifier = Modifier.padding(8.dp),
                    )
                }
            }
        }
    }
}

val hoursList = IntRange(0, 23).map { it.toString() }
val minutesList = IntRange(0, 59).map { it.toString() }
