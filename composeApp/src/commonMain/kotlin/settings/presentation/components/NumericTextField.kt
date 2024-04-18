package settings.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import util.SuffixTransformation

@Composable
fun NumericTextField(
    value: String = "100",
    onServiceReminderAdded: (String) -> Unit,
    defaultSuffix: String = "km",
    isError: Boolean = false,
    errorMessage: String? = null,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
    ) {
        CustomTextField(
            text = value,
            placeHolder = value,
            onValueChange = {
                onServiceReminderAdded(it)
            },
            requiresDataValidation = false,
            keyboardType = KeyboardType.Number,
            singleLine = true,
            isError = isError,
            errorMessage = errorMessage,
            visualTransformation = SuffixTransformation(defaultSuffix),
        )
    }
}