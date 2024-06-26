package bikes.presentation.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.cancel_dialog_action
import bikeappkmp.composeapp.generated.resources.delete_bike_dialog_text
import bikeappkmp.composeapp.generated.resources.delete_menu_action
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun ConfirmationDialog(
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
    dialogText: String = "Nukeproof Scout 290",
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(dialogText)
                        }
                        append(stringResource(Res.string.delete_bike_dialog_text))
                    },
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )
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
                        text = stringResource(Res.string.delete_menu_action),
                        onButtonClick = { onConfirmation() },
                        modifier = Modifier.padding(8.dp),
                    )
                }
            }
        }
    }
}