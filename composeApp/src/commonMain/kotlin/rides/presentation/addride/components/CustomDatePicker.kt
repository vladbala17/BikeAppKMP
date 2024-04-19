@file:OptIn(ExperimentalResourceApi::class)

package rides.presentation.addride.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bikeappkmp.composeapp.generated.resources.Res
import bikeappkmp.composeapp.generated.resources.cancel_dialog_action
import bikeappkmp.composeapp.generated.resources.set_dialog_action
import bikes.presentation.list.components.ActionButton
import kotlinx.datetime.Clock
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun CustomDatePicker(onDateSelected: (Long) -> Unit = {}, onDismissDialog: () -> Unit = {}) {
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= Clock.System.now().toEpochMilliseconds()
        }
    })

    val selectedDate = datePickerState.selectedDateMillis?.let {
        it
    } ?: Long.MAX_VALUE

    DatePickerDialog(
        onDismissRequest = onDismissDialog,
        confirmButton = {
            ActionButton(
                text = stringResource(Res.string.set_dialog_action),
                onButtonClick = { onDateSelected(selectedDate) },
                modifier = Modifier.padding(8.dp),
            )
        },
        dismissButton = {
            TextButton(
                onClick = { onDismissDialog() },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(stringResource(Res.string.cancel_dialog_action))
            }
        }) {
        DatePicker(
            state = datePickerState, showModeToggle = false
        )
    }
}